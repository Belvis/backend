package com.poly.sof3021.ph29788.repositories.product;

import com.poly.sof3021.ph29788.entities.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
}
