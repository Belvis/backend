package com.poly.sof3021.ph29788.entities.user;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import com.poly.sof3021.ph29788.common.enums.Gender;
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

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer extends PrimaryEntity {

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
