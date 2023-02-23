package com.stefanovich.productback.service.cache;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import java.util.Optional;
import org.springframework.scheduling.annotation.Async;

public interface ItemCache {

  Optional<PageDto<Item>> get(ItemSearchFilterDto cacheKey);

  @Async
  void put(ItemSearchFilterDto cacheKey, PageDto<Item> cacheValue);

  @Async
  void clear();
}
