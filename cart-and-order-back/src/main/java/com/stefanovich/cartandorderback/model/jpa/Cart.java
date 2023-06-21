package com.stefanovich.cartandorderback.model.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
//  User user;
  @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
  List<CartItemPosition> cartItems;

}
