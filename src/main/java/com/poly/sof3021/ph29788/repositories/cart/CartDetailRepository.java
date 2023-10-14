package com.poly.sof3021.ph29788.repositories.cart;

import com.poly.sof3021.ph29788.entities.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
