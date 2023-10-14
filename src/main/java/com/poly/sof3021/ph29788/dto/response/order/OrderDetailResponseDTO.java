package com.poly.sof3021.ph29788.dto.response.order;

import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDetailResponseDTO {
    private Long id;
//    private OrderResponseDTO order;
    private ProductDetailResponseDTO productDetail;
    private Integer quantity;
    private Double price;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
