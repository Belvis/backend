package com.poly.sof3021.ph29788.dto.response.image;

import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImageResponseDTO {

    private Long id;

    private ProductDetailResponseDTO productDetailResponseDTO;

    private String url;

    private Long createdAt;

    private Long updatedAt;

    private String createdBy;

    private String updatedBy;

}
