package com.poly.sof3021.ph29788.dto.mapper.cart;

import com.poly.sof3021.ph29788.dto.mapper.user.CustomerMapper;
import com.poly.sof3021.ph29788.dto.request.cart.CartRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartResponseDTO;
import com.poly.sof3021.ph29788.entities.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerMapper.class})
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toEntity(CartRequestDTO cartRequestDTO);

    CartResponseDTO toResponseDTO(Cart cart);

}
