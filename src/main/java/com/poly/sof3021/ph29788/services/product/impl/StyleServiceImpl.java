package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.StyleMapper;
import com.poly.sof3021.ph29788.dto.request.product.StyleRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.StyleResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Style;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.StyleRepository;
import com.poly.sof3021.ph29788.services.product.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Override
    public Page<StyleResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Style> stylePage = styleRepository.findAll(pageable);
        return stylePage.map(StyleMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public StyleResponseDTO getById(Long id) {
        Optional<Style> styleOptional = styleRepository.findById(id);
        if (styleOptional.isPresent()) {
            Style style = styleOptional.get();
            return StyleMapper.INSTANCE.toResponseDTO(style);
        } else {
            throw new ResourceNotFoundException("Style with id " + id + " not found");
        }
    }

    @Override
    public StyleResponseDTO save(StyleRequestDTO requestDTO) {
        Style style = StyleMapper.INSTANCE.toEntity(requestDTO);
        return StyleMapper.INSTANCE.toResponseDTO(this.styleRepository.save(style));
    }

    @Override
    public StyleResponseDTO update(StyleRequestDTO requestDTO, Long id) {
        Optional<Style> checkExistingStyle = styleRepository.findById(id);
        if (!checkExistingStyle.isPresent()) {
            throw new ResourceNotFoundException("Style with id " + id + " Not Found");
        }

        Style existingStyle = checkExistingStyle.get();
        Style updatedStyle = StyleMapper.INSTANCE.toEntity(requestDTO);
        updatedStyle.setId(id);
        updatedStyle.setCreatedAt(existingStyle.getCreatedAt());

        updatedStyle = styleRepository.save(updatedStyle);
        return StyleMapper.INSTANCE.toResponseDTO(updatedStyle);
    }

    @Override
    public void delete(Long id) {
        styleRepository.deleteById(id);
    }
}
