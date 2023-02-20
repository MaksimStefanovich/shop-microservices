package com.stefanovich.productback.repository.redis;

import com.stefanovich.productback.model.redis.ItemSearchFilterCache;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ItemSearchFilterRepository extends
    KeyValueRepository<ItemSearchFilterCache, String> {

}
