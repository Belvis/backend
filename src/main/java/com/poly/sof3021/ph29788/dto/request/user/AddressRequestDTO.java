package com.poly.sof3021.ph29788.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {
    private Long id;
    private CustomerRequestDTO customer;
    private String line;
    private String city;
    private String province;
    private String country;
    private Boolean isDefault;
}
