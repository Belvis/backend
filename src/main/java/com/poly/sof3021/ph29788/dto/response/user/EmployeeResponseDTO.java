package com.poly.sof3021.ph29788.dto.response.user;

import com.poly.sof3021.ph29788.common.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeResponseDTO {
    private Long id;
    private String email;
    private Role role;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
