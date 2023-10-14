package com.poly.sof3021.ph29788.dto.response.cart;

import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartResponseDTO {
    private Long id;
    private CustomerRequestDTO customerRequestDTO;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
