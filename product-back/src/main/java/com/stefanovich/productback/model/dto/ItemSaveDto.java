package com.stefanovich.productback.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemSaveDto {

  String name;
  BigDecimal price;
}