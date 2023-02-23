package com.stefanovich.productback.model.dto;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ItemSearchFilterDto {

  String name;
  BigDecimal price;
  int size = 10;
  int page = 0;

  public Optional<String> getNameO() {
    return Optional.ofNullable(name);
  }

  public Optional<BigDecimal> getPriceO() {
    return Optional.ofNullable(price);
  }
}
