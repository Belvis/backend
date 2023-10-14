package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.SizeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.SizeResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Size;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SizeMapper {
    SizeMapper INSTANCE = Mappers.getMapper(SizeMapper.class);

    Size toEntity(SizeRequestDTO sizeRequestDTO);

    SizeResponseDTO toResponseDTO(Size size);

}
