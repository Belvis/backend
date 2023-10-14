package com.poly.sof3021.ph29788.dto.request.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderHistoryRequestDTO {

    private OrderRequestDTO order;

    private String actionDescription;

}

