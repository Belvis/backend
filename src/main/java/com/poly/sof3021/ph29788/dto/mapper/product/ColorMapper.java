package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.ColorRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ColorResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    Color toEntity(ColorRequestDTO colorRequestDTO);

    ColorResponseDTO toResponseDTO(Color color);

}
