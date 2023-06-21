package com.stefanovich.cartandorderback.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemPosition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @ManyToOne
  Cart cart;
  Integer count;
  @ManyToOne
  CartItem item;

}
