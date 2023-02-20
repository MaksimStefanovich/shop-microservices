package com.stefanovich.productback.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.repository.mongo.ItemRepository;
import com.stefanovich.productback.service.cache.ItemCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemCache itemCache;

  public void saveItem(ItemSaveDto itemSaveDto) {
    itemRepository.insert(Item.builder()
        .name(itemSaveDto.getName())
        .price(itemSaveDto.getPrice()).build());
    itemCache.clear();
  }

  public Page<Item> getAllItemsByFilters(ItemSearchFilterDto itemSearchFilter, int size, int page) {
    Optional.ofNullable(itemSearchFilter)
        .orElseThrow(() -> new IllegalArgumentException("filter must be not null"));

//    Optional<Page<Item>> cacheItems = itemCache.get(itemSearchFilter);
//    if (cacheItems.isPresent()) {
//      log.debug("use cache result");
//      return cacheItems.get();
//    }
//    log.debug("cache miss");
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
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Item> result = filters.stream()
        .reduce(BooleanExpression::and)
        .map(booleanExpression -> itemRepository.findAll(booleanExpression, pageRequest))
        .orElse(itemRepository.findAll(pageRequest));

//    itemCache.put(itemSearchFilter, result);
    return result;
  }
}

