package com.stefanovich.cartandorderback.service;

import com.stefanovich.cartandorderback.client.ItemClient;
import com.stefanovich.cartandorderback.model.dto.ItemDto;
import com.stefanovich.cartandorderback.model.jpa.Cart;
import com.stefanovich.cartandorderback.model.jpa.CartItem;
import com.stefanovich.cartandorderback.model.jpa.CartItemPosition;
import com.stefanovich.cartandorderback.repository.CartItemPositionRepository;
import com.stefanovich.cartandorderback.repository.CartItemRepository;
import com.stefanovich.cartandorderback.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final CartItemPositionRepository cartItemPositionRepository;
  private final ItemClient itemClient;

  @Transactional
  public void addItemToCart(Integer cartId, String itemId, Integer count) {
    Cart cart = getCartById(cartId);
    CartItem cartItem = getCartItemById(itemId);

    CartItemPosition cartItemPosition = CartItemPosition.builder()
        .cart(cart)
        .item(cartItem)
        .count(count)
        .build();

    cartItemPositionRepository.save(cartItemPosition);
  }

  public CartItem getCartItemById(String itemId) {
    return cartItemRepository.findById(itemId)
        .orElseGet(() -> cartItemRepository.save(requestItemById(itemId)));
  }

  public CartItem requestItemById(String itemId) {
    ItemDto itemDto = itemClient.getById(itemId);
    return CartItem.builder()
        .id(itemDto.getId())
        .name(itemDto.getName())
        .price(itemDto.getPrice())
        .build();
  }

  /**
   * Get cart by Id
   *
   * @param cartId id  of cart
   * @return Cart
   * @throws EntityNotFoundException if cart with id doesn't exist
   */
  public Cart getCartById(Integer cartId) throws EntityNotFoundException {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new EntityNotFoundException("Cart with id = " + cartId + " not found"));
  }

  public boolean existsCartItemById(String id) {
    return cartItemRepository.existsById(id);
  }

  public CartItem replaceCartItem(CartItem cartItem) {
    return cartItemRepository.save(cartItem);
  }
}
