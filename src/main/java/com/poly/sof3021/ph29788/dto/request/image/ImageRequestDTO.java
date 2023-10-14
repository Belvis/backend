package com.poly.sof3021.ph29788.dto.request.image;

import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequestDTO {

    private ProductDetailRequestDTO productDetail;

    private String url;

    private Boolean isCover;

}
