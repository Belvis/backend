package com.poly.sof3021.ph29788.dto.response.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderHistoryResponseDTO {
    private Long id;
    private OrderResponseDTO orderResponseDTO;
    private String actionDescription;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
}
