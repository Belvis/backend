package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.MaterialRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.MaterialResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    Material toEntity(MaterialRequestDTO materialRequestDTO);

    MaterialResponseDTO toResponseDTO(Material material);

}
