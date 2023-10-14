package com.poly.sof3021.ph29788.dto.mapper.order;

import com.poly.sof3021.ph29788.dto.request.order.OrderHistoryRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderHistoryResponseDTO;
import com.poly.sof3021.ph29788.entities.order.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {OrderMapper.class})
public interface OrderHistoryMapper {
    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    OrderHistory toEntity(OrderHistoryRequestDTO requestDTO);

    OrderHistoryResponseDTO toResponseDTO(OrderHistory orderHistory);

}
