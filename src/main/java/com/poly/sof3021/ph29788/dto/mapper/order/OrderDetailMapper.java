package com.poly.sof3021.ph29788.dto.mapper.order;

import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.request.order.OrderDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.order.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {OrderMapper.class, ProductDetailMapper.class})
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    OrderDetail toEntity(OrderDetailRequestDTO requestDTO);

    OrderDetailResponseDTO toResponseDTO(OrderDetail orderDetail);

}
