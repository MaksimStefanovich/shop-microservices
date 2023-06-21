package com.stefanovich.cartandorderback.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddItemToCartDto {

  Integer cartId; //FIXME remove after auth
  String itemId;
  Integer count;
}
