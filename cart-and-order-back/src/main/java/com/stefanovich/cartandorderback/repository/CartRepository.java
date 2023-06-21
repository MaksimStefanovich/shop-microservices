package com.stefanovich.cartandorderback.repository;

import com.stefanovich.cartandorderback.model.jpa.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
