package com.poly.sof3021.ph29788.services.user.impl;

import com.poly.sof3021.ph29788.dto.mapper.user.CustomerMapper;
import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.CustomerResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Address;
import com.poly.sof3021.ph29788.entities.user.Customer;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.user.AddressRepository;
import com.poly.sof3021.ph29788.repositories.user.CustomerRepository;
import com.poly.sof3021.ph29788.services.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<CustomerResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage.map(CustomerMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return CustomerMapper.INSTANCE.toResponseDTO(customer);
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerRequestDTO);
        return CustomerMapper.INSTANCE.toResponseDTO(this.customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO requestDTO, Long id) {
        Optional<Customer> checkExistingCustomer = customerRepository.findById(id);
        if (!checkExistingCustomer.isPresent()) {
            throw new ResourceNotFoundException("Customer with id " + id + " Not Found");
        }

        Customer existingCustomer = checkExistingCustomer.get();
        Customer updatedCustomer = CustomerMapper.INSTANCE.toEntity(requestDTO);
        updatedCustomer.setId(id);
        updatedCustomer.setCreatedAt(existingCustomer.getCreatedAt());

        updatedCustomer = customerRepository.save(updatedCustomer);
        return CustomerMapper.INSTANCE.toResponseDTO(updatedCustomer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
