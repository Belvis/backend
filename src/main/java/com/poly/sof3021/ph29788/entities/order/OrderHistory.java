package com.poly.sof3021.ph29788.entities.order;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderHistory extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "action_description")
    private String actionDescription;

}
