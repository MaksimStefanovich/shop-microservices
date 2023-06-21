package com.stefanovich.cartandorderback.client;

import com.stefanovich.cartandorderback.model.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${item-service.url}",name = "itemClient")
public interface ItemClient {

  @GetMapping("{id}")
  ItemDto getById(@PathVariable String id);
}
