package com.poly.sof3021.ph29788.entities.product;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product extends PrimaryEntity {

    @OneToMany(mappedBy = "product")
    private Set<ProductDetail> productDetails;

    @Column
    private String name;

}
