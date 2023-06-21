package com.stefanovich.productback.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
public class ItemReplaceDto {

  ObjectId id;
  String name;
  BigDecimal price;
  String category;
}
