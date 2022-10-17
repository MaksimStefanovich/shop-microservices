package com.stefanovich.productback.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;


@Data
@Builder
public class ItemSaveDto {
  String name;
  BigDecimal price;
}