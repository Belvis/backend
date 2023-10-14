package com.poly.sof3021.ph29788.dto.request.order;

import com.poly.sof3021.ph29788.common.enums.OrderStatus;
import com.poly.sof3021.ph29788.dto.request.user.AddressRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.EmployeeRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequestDTO {

    private CustomerRequestDTO customer;
    private EmployeeRequestDTO employee;
    private String phoneNumber;
    private AddressRequestDTO address;
    private Double totalMoney;
    private Double shippingMoney;
    private Long confirmationDate;
    private Long expectedDeliveryDate;
    private Long deliveryStartDate;
    private Long receivedDate;
    private Long completionDate;
    private OrderStatus status;
    private String note;
    private List<OrderDetailRequestDTO> orderDetails;

}

