package com.poly.sof3021.ph29788.dto.mapper.user;

import com.poly.sof3021.ph29788.dto.request.user.AddressRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.AddressResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerMapper.class})
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO toResponseDTO(Address address);

}
