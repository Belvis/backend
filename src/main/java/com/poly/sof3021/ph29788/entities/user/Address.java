package com.poly.sof3021.ph29788.entities.user;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import com.poly.sof3021.ph29788.entities.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "address")
    private Set<Order> orders;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String line;

    @Column
    private String city;

    @Column
    private String province;

    @Column
    private String country;

}
