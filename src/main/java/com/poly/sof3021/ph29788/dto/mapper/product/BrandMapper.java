package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.BrandResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toEntity(BrandRequestDTO brandRequestDTO);

    BrandResponseDTO toResponseDTO(Brand brand);

}
