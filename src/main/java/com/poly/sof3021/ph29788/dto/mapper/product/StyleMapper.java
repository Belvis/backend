package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.StyleRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.StyleResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Style;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StyleMapper {
    StyleMapper INSTANCE = Mappers.getMapper(StyleMapper.class);

    Style toEntity(StyleRequestDTO styleRequestDTO);

    StyleResponseDTO toResponseDTO(Style style);

}
