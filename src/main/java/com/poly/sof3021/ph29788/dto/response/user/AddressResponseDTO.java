package com.poly.sof3021.ph29788.dto.response.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddressResponseDTO {
    private Long id;
    private CustomerResponseDTO customer;
    private String line;
    private String city;
    private String province;
    private String country;
    private Boolean isDefault;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
