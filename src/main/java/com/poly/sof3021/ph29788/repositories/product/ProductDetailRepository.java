package com.poly.sof3021.ph29788.repositories.product;

import com.poly.sof3021.ph29788.entities.product.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    Page<ProductDetail> findByProduct_Id(Long productId, Pageable pageable);

}
