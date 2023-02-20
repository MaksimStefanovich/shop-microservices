package com.stefanovich.productback.service.cache;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;

public interface ItemCache {

  Optional<Page<Item>> get(ItemSearchFilterDto cacheKey);

  @Async
  void put(ItemSearchFilterDto cacheKey, Page<Item> cacheValue);

  @Async
  void clear();
}
