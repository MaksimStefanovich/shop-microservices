package com.stefanovich.cartandorderback.api;

import com.stefanovich.cartandorderback.model.dto.AddItemToCartDto;
import com.stefanovich.cartandorderback.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
@Slf4j
public class CartApi {

  private final CartService cartService;

  @PostMapping
  public void addItemToCart(@RequestBody AddItemToCartDto addItemToCartDto) {
    cartService.addItemToCart(addItemToCartDto.getCartId(), addItemToCartDto.getItemId(),
        addItemToCartDto.getCount());

  }
}
