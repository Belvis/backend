package com.poly.sof3021.ph29788.dto.request.product;

import com.poly.sof3021.ph29788.common.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailRequestDTO {
    private Long id;
    private StyleRequestDTO style;
    private SizeRequestDTO size;
    private ProductRequestDTO product;
    private MaterialRequestDTO material;
    private BrandRequestDTO brand;
    private ColorRequestDTO color;
    private String description;
    private Gender gender;
    private Double price;
    private Integer quantity;
}
