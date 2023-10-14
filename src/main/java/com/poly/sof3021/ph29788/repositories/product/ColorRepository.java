package com.poly.sof3021.ph29788.repositories.product;

import com.poly.sof3021.ph29788.entities.product.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
