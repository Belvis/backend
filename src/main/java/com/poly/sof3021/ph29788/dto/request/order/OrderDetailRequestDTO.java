package com.poly.sof3021.ph29788.dto.request.order;

import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequestDTO {
//    private OrderRequestDTO order;
    private ProductDetailRequestDTO productDetail;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
}
