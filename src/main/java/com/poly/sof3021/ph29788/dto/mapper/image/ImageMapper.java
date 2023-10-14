package com.poly.sof3021.ph29788.dto.mapper.image;

import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.request.image.ImageRequestDTO;
import com.poly.sof3021.ph29788.dto.response.image.ImageResponseDTO;
import com.poly.sof3021.ph29788.entities.image.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductDetailMapper.class})
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    Image toEntity(ImageRequestDTO requestDTO);

    ImageResponseDTO toResponseDTO(Image image);

}
