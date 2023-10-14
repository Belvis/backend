package com.poly.sof3021.ph29788.dto.response.product;

import com.poly.sof3021.ph29788.common.enums.Gender;
import com.poly.sof3021.ph29788.dto.response.image.ImageResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class ProductDetailResponseDTO {
    private Long id;
    private StyleResponseDTO style;
    private SizeResponseDTO size;
    private ProductResponseDTO product;
    private MaterialResponseDTO material;
    private BrandResponseDTO brand;
    private ColorResponseDTO color;
    private String description;
    private Gender gender;
    private Double price;
    private Integer quantity;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
