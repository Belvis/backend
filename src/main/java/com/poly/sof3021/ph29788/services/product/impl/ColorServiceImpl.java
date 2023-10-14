package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.ColorMapper;
import com.poly.sof3021.ph29788.dto.request.product.ColorRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ColorResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Color;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.ColorRepository;
import com.poly.sof3021.ph29788.services.product.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Page<ColorResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Color> colorPage = colorRepository.findAll(pageable);
        return colorPage.map(ColorMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public ColorResponseDTO getById(Long id) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isPresent()) {
            Color color = colorOptional.get();
            return ColorMapper.INSTANCE.toResponseDTO(color);
        } else {
            throw new ResourceNotFoundException("Color with id " + id + " not found");
        }
    }

    @Override
    public ColorResponseDTO save(ColorRequestDTO requestDTO) {
        Color color = ColorMapper.INSTANCE.toEntity(requestDTO);
        return ColorMapper.INSTANCE.toResponseDTO(this.colorRepository.save(color));
    }

    @Override
    public ColorResponseDTO update(ColorRequestDTO requestDTO, Long id) {
        Optional<Color> checkExistingColor = colorRepository.findById(id);
        if (!checkExistingColor.isPresent()) {
            throw new ResourceNotFoundException("Color with id " + id + " Not Found");
        }

        Color existingColor = checkExistingColor.get();
        Color updatedColor = ColorMapper.INSTANCE.toEntity(requestDTO);
        updatedColor.setId(id);
        updatedColor.setCreatedAt(existingColor.getCreatedAt());

        updatedColor = colorRepository.save(updatedColor);
        return ColorMapper.INSTANCE.toResponseDTO(updatedColor);
    }

    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}
