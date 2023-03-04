package com.stefanovich.productback.model.redis;

import com.stefanovich.productback.model.Item;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;


@RedisHash(value = "item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemEntityCache {

  String id;
  String name;
  BigDecimal price;
  String category;
  @TimeToLive
  Long ttl = (long) (60 * 60 * 4);

  public ItemEntityCache(Item item) {
    this.id = item.getId().toString();
    this.name = item.getName();
    this.price = item.getPrice();
    this.category = item.getCategory();
  }

  public Item toItem() {
    return Item.builder()
        .id(new ObjectId(id))
        .name(name)
        .price(price)
        .category(category)
        .build();
  }
}
