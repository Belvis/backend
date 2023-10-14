package com.poly.sof3021.ph29788.dto.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StyleResponseDTO {
    private Long id;
    private String name;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
