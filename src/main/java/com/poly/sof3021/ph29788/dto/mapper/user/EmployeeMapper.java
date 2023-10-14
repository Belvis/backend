package com.poly.sof3021.ph29788.dto.mapper.user;

import com.poly.sof3021.ph29788.dto.request.user.EmployeeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.EmployeeResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO toResponseDTO(Employee employee);

}
