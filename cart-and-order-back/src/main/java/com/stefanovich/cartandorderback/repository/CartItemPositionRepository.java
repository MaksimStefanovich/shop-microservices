package com.stefanovich.cartandorderback.repository;

import com.stefanovich.cartandorderback.model.jpa.CartItemPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemPositionRepository extends JpaRepository<CartItemPosition, Integer> {

}
