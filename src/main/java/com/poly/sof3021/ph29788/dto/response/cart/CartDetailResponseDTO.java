package com.poly.sof3021.ph29788.dto.response.cart;

import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartDetailResponseDTO {
    private Long id;
    private ProductDetailResponseDTO productDetailResponseDTO;
    private CartDetailResponseDTO cartDetailResponseDTO;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
