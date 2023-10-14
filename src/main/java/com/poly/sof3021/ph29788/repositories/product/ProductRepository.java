package com.poly.sof3021.ph29788.repositories.product;

import com.poly.sof3021.ph29788.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
