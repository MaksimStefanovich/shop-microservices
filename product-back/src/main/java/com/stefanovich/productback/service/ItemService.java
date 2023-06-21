package com.stefanovich.productback.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stefanovich.productback.aop.annotation.MyCache;
import com.stefanovich.productback.kafka.producer.ItemKafkaProducer;
import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import com.stefanovich.productback.repository.mongo.ItemRepository;
import com.stefanovich.productback.service.cache.ItemCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemCache itemCache;
  private final ItemKafkaProducer itemKafkaProducer;

  public void saveItem(ItemSaveDto itemSaveDto) {
    itemRepository.insert(Item.builder()
        .name(itemSaveDto.getName())
        .price(itemSaveDto.getPrice()).build());
    itemCache.clear();
  }

  @MyCache
  public PageDto<Item> getAllItemsByFilters(ItemSearchFilterDto itemSearchFilter) {
    Optional.ofNullable(itemSearchFilter)
        .orElseThrow(() -> new IllegalArgumentException("filter must be not null"));


    List<BooleanExpression> filters = new ArrayList<>();
    itemSearchFilter.getNameO().ifPresent(n -> filters.add(ItemRepository.nameLikeIgnoreCase(n)));
    itemSearchFilter.getPriceO().ifPresent(p -> filters.add(ItemRepository.priceEq(p)));

//    Optional.ofNullable(categories).ifPresent(c -> {
//      List<BooleanExpression> categoryFilter = new ArrayList<>();
//      for (String category : c) {
//        categoryFilter.add(ItemRepository.categoryEq(category));
//      }
//      categoryFilter.stream().reduce(BooleanExpression::or).ifPresent(filters::add);
//    });
    PageRequest pageRequest = PageRequest.of(itemSearchFilter.getPage(),
        itemSearchFilter.getSize());
    Page<Item> result = filters.stream()
        .reduce(BooleanExpression::and)
        .map(booleanExpression -> itemRepository.findAll(booleanExpression, pageRequest))
        .orElse(itemRepository.findAll(pageRequest));
    PageDto<Item> resultDto = new PageDto<>(result);
    return resultDto;
//    throw new RuntimeException("TEST EXCEPTION");
  }

  public Item replace(Item item){
    Item newItem = itemRepository.save(item);
    itemCache.clear();
    itemKafkaProducer.sendUpdate(newItem);
    return newItem;
  }

  public Item getById(ObjectId id) {
    return itemRepository.findById(id).orElseThrow();
  }
}

