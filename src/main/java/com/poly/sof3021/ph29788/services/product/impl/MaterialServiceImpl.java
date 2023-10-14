package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.MaterialMapper;
import com.poly.sof3021.ph29788.dto.request.product.MaterialRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.MaterialResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Material;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.MaterialRepository;
import com.poly.sof3021.ph29788.services.product.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Page<MaterialResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Material> materialPage = materialRepository.findAll(pageable);
        return materialPage.map(MaterialMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public MaterialResponseDTO getById(Long id) {
        Optional<Material> materialOptional = materialRepository.findById(id);
        if (materialOptional.isPresent()) {
            Material material = materialOptional.get();
            return MaterialMapper.INSTANCE.toResponseDTO(material);
        } else {
            throw new ResourceNotFoundException("Material with id " + id + " not found");
        }
    }

    @Override
    public MaterialResponseDTO save(MaterialRequestDTO requestDTO) {
        Material material = MaterialMapper.INSTANCE.toEntity(requestDTO);
        return MaterialMapper.INSTANCE.toResponseDTO(this.materialRepository.save(material));
    }

    @Override
    public MaterialResponseDTO update(MaterialRequestDTO requestDTO, Long id) {
        Optional<Material> checkExistingMaterial = materialRepository.findById(id);
        if (!checkExistingMaterial.isPresent()) {
            throw new ResourceNotFoundException("Material with id " + id + " Not Found");
        }

        Material existingMaterial = checkExistingMaterial.get();
        Material updatedMaterial = MaterialMapper.INSTANCE.toEntity(requestDTO);
        updatedMaterial.setId(id);
        updatedMaterial.setCreatedAt(existingMaterial.getCreatedAt());

        updatedMaterial = materialRepository.save(updatedMaterial);
        return MaterialMapper.INSTANCE.toResponseDTO(updatedMaterial);
    }

    @Override
    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
