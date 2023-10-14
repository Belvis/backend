package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.ProductRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponseDTO(Product product);

}
