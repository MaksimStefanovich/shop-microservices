package com.stefanovich.productback.model.redis;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "itemSearchFilter")
@Builder
@AllArgsConstructor
@Data
public class ItemSearchFilterCache {

  @Id
  private String itemSearchFilterDto; //FIXME
  private Page<ItemEntityCache> items;
  @TimeToLive()
  private Long ttl = (long) (60 * 60 * 4);
}
