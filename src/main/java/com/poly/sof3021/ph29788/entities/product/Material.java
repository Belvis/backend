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
@Table(name = "material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Material extends PrimaryEntity {

    @OneToMany(mappedBy = "material")
    private Set<ProductDetail> productDetails;

    @Column
    private String name;

}
