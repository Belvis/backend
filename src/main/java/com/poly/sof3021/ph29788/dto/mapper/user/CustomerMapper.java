package com.poly.sof3021.ph29788.dto.mapper.user;

import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.CustomerResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AddressMapper.class})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO toResponseDTO(Customer customer);

}

