package com.stefanovich.productback.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.repository.ItemRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public void saveItem(ItemSaveDto itemSaveDto) {
    itemRepository.insert(Item.builder()
        .name(itemSaveDto.getName())
        .price(itemSaveDto.getPrice()).build());
  }

  public List<Item> getAllItemsByFilters(String name, BigDecimal price, List<String> categories) {
    List<BooleanExpression> filters = new ArrayList<>();
    Optional.ofNullable(name).ifPresent(n -> filters.add(ItemRepository.nameLikeIgnoreCase(n)));
    Optional.ofNullable(price).ifPresent(p -> filters.add(ItemRepository.priceEq(p)));

    Optional.ofNullable(categories).ifPresent(c -> {
      List<BooleanExpression> categoryFilter = new ArrayList<>();
      for (String category : c) {
        categoryFilter.add(ItemRepository.categoryEq(category));
      }
      categoryFilter.stream().reduce(BooleanExpression::or).ifPresent(filters::add);
    });

    return filters.stream()
        .reduce(BooleanExpression::and)
        .map(booleanExpression -> (List<Item>) itemRepository.findAll(booleanExpression))
        .orElse(itemRepository.findAll());

  }
}
