package com.stefanovich.productback.service.cache;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.redis.ItemEntityCache;
import com.stefanovich.productback.model.redis.ItemSearchFilterCache;
import com.stefanovich.productback.repository.redis.ItemSearchFilterRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
@Slf4j
public class RedisItemCache implements ItemCache {

  private final ItemSearchFilterRepository itemSearchFilterRepository;

  @Override
  public Optional<Page<Item>> get(ItemSearchFilterDto cacheKey) {
    return itemSearchFilterRepository
        .findById(cacheKey.toString())
        .map(ItemSearchFilterCache::getItems)
        .map(
            l -> new PageImpl<>(l.getContent().stream()
                .map(ItemEntityCache::toItem)
                .collect(Collectors.toList()))
        );
  }

  @Override
  public void put(ItemSearchFilterDto cacheKey, Page<Item> cacheValue) {
    itemSearchFilterRepository.save(ItemSearchFilterCache.builder()
        .itemSearchFilterDto(cacheKey.toString())
        .items(new PageImpl<>(cacheValue.stream().map(ItemEntityCache::new).collect(Collectors.toList())))
        .build());
  }

  @Override
  public void clear() {
    itemSearchFilterRepository.deleteAll();
  }
}
