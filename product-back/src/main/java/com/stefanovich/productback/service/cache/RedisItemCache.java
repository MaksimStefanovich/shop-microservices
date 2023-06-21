package com.stefanovich.productback.service.cache;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import com.stefanovich.productback.model.redis.ItemEntityCache;
import com.stefanovich.productback.model.redis.ItemSearchFilterCache;
import com.stefanovich.productback.repository.redis.ItemSearchFilterCacheRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
@Slf4j
public class RedisItemCache implements ItemCache {

  private final ItemSearchFilterCacheRepository itemSearchFilterCacheRepository;

  @Override
  public Optional<PageDto<Item>> get(ItemSearchFilterDto cacheKey) {
    return itemSearchFilterCacheRepository
        .findById(cacheKey.toString())
        .map(ItemSearchFilterCache::getItems)
        .map(
            l -> PageDto.<Item>builder()
                .currentPage(l.getCurrentPage())
                .totalPages(l.getTotalPages())
                .currentSize(l.getCurrentSize())
                .totalElements(l.getTotalElements())
                .content(l.getContent().stream()
                    .map(ItemEntityCache::toItem)
                    .collect(Collectors.toList())).build());
  }

  @Override
  public void put(ItemSearchFilterDto cacheKey, PageDto<Item> cacheValue) {
    itemSearchFilterCacheRepository.save(ItemSearchFilterCache.builder()
        .itemSearchFilterDto(cacheKey.toString())
        .items(PageDto.<ItemEntityCache>builder()
            .currentPage(cacheValue.getCurrentPage())
            .totalPages(cacheValue.getTotalPages())
            .currentSize(cacheValue.getCurrentSize())
            .totalElements(cacheValue.getTotalElements())
            .content(cacheValue.getContent().stream().map(ItemEntityCache::new)
                .collect(Collectors.toList())).build()).build());
  }

  @Override
  public void clear() {
    itemSearchFilterCacheRepository.deleteAll();
  }
}
