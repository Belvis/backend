package com.poly.sof3021.ph29788.dto.response.order;

import com.poly.sof3021.ph29788.common.enums.OrderStatus;
import com.poly.sof3021.ph29788.dto.request.order.OrderDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.AddressResponseDTO;
import com.poly.sof3021.ph29788.dto.response.user.CustomerResponseDTO;
import com.poly.sof3021.ph29788.dto.response.user.EmployeeResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;
    private CustomerResponseDTO customer;
    private EmployeeResponseDTO employee;
    private String phoneNumber;
    private AddressResponseDTO address;
    private Double totalMoney;
    private Double shippingMoney;
    private Long confirmationDate;
    private Long expectedDeliveryDate;
    private Long deliveryStartDate;
    private Long receivedDate;
    private Long completionDate;
    private OrderStatus status;
    private String note;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
    private List<OrderDetailResponseDTO> orderDetails;

}
