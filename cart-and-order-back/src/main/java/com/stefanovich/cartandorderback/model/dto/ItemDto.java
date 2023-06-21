package com.stefanovich.cartandorderback.model.dto;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

  String id;
  String name;
  BigDecimal price;

}
