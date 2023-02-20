package com.stefanovich.productback.model.redis;

import com.stefanovich.productback.model.Item;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntityCache {

  String id;
  String name;
  BigDecimal price;
  String category;

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
