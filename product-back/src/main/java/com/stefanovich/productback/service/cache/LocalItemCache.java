package com.stefanovich.productback.service.cache;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocalItemCache implements ItemCache{

  private final Map<ItemSearchFilterDto, PageDto<Item>> itemCache = new ConcurrentHashMap<>();

  public Optional<PageDto<Item>> get(ItemSearchFilterDto cacheKey) {
    Optional<PageDto<Item>> items = Optional.ofNullable(itemCache.get(cacheKey));
    items.ifPresent(i -> log.debug("get Items from cache by key = {}", cacheKey));
    return items;
  }

  public void put(ItemSearchFilterDto cacheKey, PageDto<Item> cacheValue) {
    log.debug("put Items to cache by key = {}, and value = {}", cacheKey, cacheValue);
    itemCache.put(cacheKey, cacheValue);
  }

  @Override
  public void clear() {
    itemCache.clear();
  }
}
