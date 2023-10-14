package com.poly.sof3021.ph29788.entities.user;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import com.poly.sof3021.ph29788.common.enums.Gender;
import com.poly.sof3021.ph29788.common.enums.Role;
import com.poly.sof3021.ph29788.entities.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee extends PrimaryEntity {

    @OneToMany(mappedBy = "employee")
    private Set<Order> orders;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
