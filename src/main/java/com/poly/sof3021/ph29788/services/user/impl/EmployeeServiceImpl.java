package com.poly.sof3021.ph29788.services.user.impl;

import com.poly.sof3021.ph29788.dto.mapper.user.EmployeeMapper;
import com.poly.sof3021.ph29788.dto.request.user.EmployeeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.EmployeeResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Employee;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.user.EmployeeRepository;
import com.poly.sof3021.ph29788.services.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public EmployeeResponseDTO getById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return EmployeeMapper.INSTANCE.toResponseDTO(employee);
        } else {
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
    }

    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO requestDTO) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(requestDTO);
        return EmployeeMapper.INSTANCE.toResponseDTO(this.employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseDTO update(EmployeeRequestDTO requestDTO, Long id) {
        Optional<Employee> checkExistingEmployee = employeeRepository.findById(id);
        if (!checkExistingEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + id + " Not Found");
        }

        Employee existingEmployee = checkExistingEmployee.get();
        Employee updatedEmployee = EmployeeMapper.INSTANCE.toEntity(requestDTO);
        updatedEmployee.setId(id);
        updatedEmployee.setCreatedAt(existingEmployee.getCreatedAt());

        updatedEmployee = employeeRepository.save(updatedEmployee);
        return EmployeeMapper.INSTANCE.toResponseDTO(updatedEmployee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
