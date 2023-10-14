package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.BrandMapper;
import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.BrandResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Brand;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.BrandRepository;
import com.poly.sof3021.ph29788.services.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<BrandResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        return brandPage.map(BrandMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public BrandResponseDTO getById(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            return BrandMapper.INSTANCE.toResponseDTO(brand);
        } else {
            throw new ResourceNotFoundException("Brand with id " + id + " not found");
        }
    }

    @Override
    public BrandResponseDTO save(BrandRequestDTO requestDTO) {
        Brand brand = BrandMapper.INSTANCE.toEntity(requestDTO);
        return BrandMapper.INSTANCE.toResponseDTO(this.brandRepository.save(brand));
    }

    @Override
    public BrandResponseDTO update(BrandRequestDTO requestDTO, Long id) {
        Optional<Brand> checkExistingBrand = brandRepository.findById(id);
        if (!checkExistingBrand.isPresent()) {
            throw new ResourceNotFoundException("Brand with id " + id + " Not Found");
        }

        Brand existingBrand = checkExistingBrand.get();
        Brand updatedBrand = BrandMapper.INSTANCE.toEntity(requestDTO);
        updatedBrand.setId(id);
        updatedBrand.setCreatedAt(existingBrand.getCreatedAt());

        updatedBrand = brandRepository.save(updatedBrand);
        return BrandMapper.INSTANCE.toResponseDTO(updatedBrand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
