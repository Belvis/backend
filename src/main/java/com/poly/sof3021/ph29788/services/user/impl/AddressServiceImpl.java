package com.poly.sof3021.ph29788.services.user.impl;

import com.poly.sof3021.ph29788.dto.mapper.user.AddressMapper;
import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.AddressRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.AddressResponseDTO;
import com.poly.sof3021.ph29788.entities.user.Address;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.user.AddressRepository;
import com.poly.sof3021.ph29788.repositories.user.CustomerRepository;
import com.poly.sof3021.ph29788.services.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<AddressResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Address> addressPage = addressRepository.findAll(pageable);
        return addressPage.map(AddressMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public AddressResponseDTO getById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            return AddressMapper.INSTANCE.toResponseDTO(address);
        } else {
            throw new ResourceNotFoundException("Address with id " + id + " not found");
        }
    }

    @Override
    public AddressResponseDTO save(AddressRequestDTO requestDTO) {
        Address address = AddressMapper.INSTANCE.toEntity(requestDTO);
        Optional.ofNullable(requestDTO.getCustomer())
                .map(CustomerRequestDTO::getId)
                .flatMap(customerRepository::findById)
                .ifPresent(address::setCustomer);
        System.out.println(address.getCustomer().getDateOfBirth());
        return AddressMapper.INSTANCE.toResponseDTO(this.addressRepository.save(address));
    }

    @Override
    public AddressResponseDTO update(AddressRequestDTO requestDTO, Long id) {
        Optional<Address> checkExistingAddress = addressRepository.findById(id);
        if (!checkExistingAddress.isPresent()) {
            throw new ResourceNotFoundException("Address with id " + id + " Not Found");
        }

        Address existingAddress = checkExistingAddress.get();
        Address updatedAddress = AddressMapper.INSTANCE.toEntity(requestDTO);
        Optional.ofNullable(requestDTO.getCustomer())
                .map(CustomerRequestDTO::getId)
                .flatMap(customerRepository::findById)
                .ifPresent(updatedAddress::setCustomer);
        updatedAddress.setId(id);
        updatedAddress.setCreatedAt(existingAddress.getCreatedAt());

        updatedAddress = addressRepository.save(updatedAddress);
        return AddressMapper.INSTANCE.toResponseDTO(updatedAddress);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
