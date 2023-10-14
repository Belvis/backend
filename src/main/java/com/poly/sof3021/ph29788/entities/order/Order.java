package com.poly.sof3021.ph29788.entities.order;

import com.poly.sof3021.ph29788.common.core.PrimaryEntity;
import com.poly.sof3021.ph29788.common.enums.OrderStatus;
import com.poly.sof3021.ph29788.common.enums.OrderType;
import com.poly.sof3021.ph29788.entities.user.Address;
import com.poly.sof3021.ph29788.entities.user.Customer;
import com.poly.sof3021.ph29788.entities.user.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shop_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Order extends PrimaryEntity {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "shipping_money")
    private Double shippingMoney;

    @Column(name = "confirmation_date")
    private Long confirmationDate;

    @Column(name = "expected_delivery_date")
    private Long expectedDeliveryDate;

    @Column(name = "delivery_start_date")
    private Long deliveryStartDate;

    @Column(name = "received_date")
    private Long receivedDate;

    @Column(name = "completion_date")
    private Long completionDate;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column
    private String note;

}
