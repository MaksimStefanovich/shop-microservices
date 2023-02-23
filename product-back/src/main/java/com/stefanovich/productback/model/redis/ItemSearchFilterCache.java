package com.stefanovich.productback.model.redis;

import com.stefanovich.productback.model.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "itemSearchFilter")
@Builder
@AllArgsConstructor
@Data
public class ItemSearchFilterCache {

  @Id
  private String itemSearchFilterDto; //FIXME
  private PageDto<ItemEntityCache> items;
  @TimeToLive()
  private Long ttl = (long) (60 * 60 * 4);
}
