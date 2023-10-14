package com.poly.sof3021.ph29788.repositories.cart;

import com.poly.sof3021.ph29788.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
