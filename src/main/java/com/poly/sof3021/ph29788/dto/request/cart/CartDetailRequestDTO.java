package com.poly.sof3021.ph29788.dto.request.cart;

import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailRequestDTO {
    private ProductDetailRequestDTO productDetail;
    private CartRequestDTO cart;
}
