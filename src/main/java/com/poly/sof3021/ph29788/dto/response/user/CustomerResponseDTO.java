package com.poly.sof3021.ph29788.dto.response.user;

import com.poly.sof3021.ph29788.common.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerResponseDTO {
    private Long id;
    private String fullName;
    private Long dateOfBirth;
    private String email;
    private String password;
    private Gender gender;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
