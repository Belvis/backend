package com.poly.sof3021.ph29788.dto.request.cart;

import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDTO {
    private CustomerRequestDTO customer;
}
