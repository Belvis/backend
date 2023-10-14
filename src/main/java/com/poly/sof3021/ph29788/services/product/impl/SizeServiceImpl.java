package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.SizeMapper;
import com.poly.sof3021.ph29788.dto.request.product.SizeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.SizeResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Size;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.SizeRepository;
import com.poly.sof3021.ph29788.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Page<SizeResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Size> sizePage = sizeRepository.findAll(pageable);
        return sizePage.map(SizeMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public SizeResponseDTO getById(Long id) {
        Optional<Size> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isPresent()) {
            Size size = sizeOptional.get();
            return SizeMapper.INSTANCE.toResponseDTO(size);
        } else {
            throw new ResourceNotFoundException("Size with id " + id + " not found");
        }
    }

    @Override
    public SizeResponseDTO save(SizeRequestDTO requestDTO) {
        Size size = SizeMapper.INSTANCE.toEntity(requestDTO);
        return SizeMapper.INSTANCE.toResponseDTO(this.sizeRepository.save(size));
    }

    @Override
    public SizeResponseDTO update(SizeRequestDTO requestDTO, Long id) {
        Optional<Size> checkExistingSize = sizeRepository.findById(id);
        if (!checkExistingSize.isPresent()) {
            throw new ResourceNotFoundException("Size with id " + id + " Not Found");
        }

        Size existingSize = checkExistingSize.get();
        Size updatedSize = SizeMapper.INSTANCE.toEntity(requestDTO);
        updatedSize.setId(id);
        updatedSize.setCreatedAt(existingSize.getCreatedAt());

        updatedSize = sizeRepository.save(updatedSize);
        return SizeMapper.INSTANCE.toResponseDTO(updatedSize);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }
}
