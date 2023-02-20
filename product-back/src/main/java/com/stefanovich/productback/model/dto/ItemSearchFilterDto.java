package com.stefanovich.productback.model.dto;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Data;

@Data
public class ItemSearchFilterDto {

  String name;
  BigDecimal price;

  public Optional<String> getNameO() {
    return Optional.ofNullable(name);
  }

  public Optional<BigDecimal> getPriceO() {
    return Optional.ofNullable(price);
  }
}
