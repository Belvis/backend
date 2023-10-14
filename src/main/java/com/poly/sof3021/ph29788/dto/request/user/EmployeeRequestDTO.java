package com.poly.sof3021.ph29788.dto.request.user;

import com.poly.sof3021.ph29788.common.enums.Gender;
import com.poly.sof3021.ph29788.common.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
}
