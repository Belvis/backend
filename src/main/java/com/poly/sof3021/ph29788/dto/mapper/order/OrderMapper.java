package com.poly.sof3021.ph29788.dto.mapper.order;

import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.mapper.user.AddressMapper;
import com.poly.sof3021.ph29788.dto.mapper.user.CustomerMapper;
import com.poly.sof3021.ph29788.dto.mapper.user.EmployeeMapper;
import com.poly.sof3021.ph29788.dto.request.order.OrderRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderResponseDTO;
import com.poly.sof3021.ph29788.entities.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerMapper.class, EmployeeMapper.class, AddressMapper.class, OrderDetailMapper.class, ProductDetailMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderRequestDTO requestDTO);

    OrderResponseDTO toResponseDTO(Order order);

}

