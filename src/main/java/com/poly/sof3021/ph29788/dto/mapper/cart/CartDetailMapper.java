package com.poly.sof3021.ph29788.dto.mapper.cart;

import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.request.cart.CartDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.cart.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CartMapper.class, ProductDetailMapper.class})
public interface CartDetailMapper {
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);

    CartDetail toEntity(CartDetailRequestDTO cartDetailRequestDTO);

    CartDetailResponseDTO toResponseDTO(CartDetail cartDetail);

}
