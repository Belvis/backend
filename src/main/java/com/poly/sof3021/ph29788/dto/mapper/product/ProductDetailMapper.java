package com.poly.sof3021.ph29788.dto.mapper.product;

import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.product.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        StyleMapper.class,
        SizeMapper.class,
        ProductMapper.class,
        MaterialMapper.class,
        BrandMapper.class,
        ColorMapper.class
})
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);

    ProductDetail toEntity(ProductDetailRequestDTO productDetailRequestDTO);

    ProductDetailResponseDTO toResponseDTO(ProductDetail productDetail);
}
