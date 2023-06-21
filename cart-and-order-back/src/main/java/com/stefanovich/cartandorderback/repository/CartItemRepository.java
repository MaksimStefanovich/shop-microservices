package com.stefanovich.cartandorderback.repository;

import com.stefanovich.cartandorderback.model.jpa.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

}
