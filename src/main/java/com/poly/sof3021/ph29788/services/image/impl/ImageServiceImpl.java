package com.poly.sof3021.ph29788.services.image.impl;

import com.poly.sof3021.ph29788.dto.mapper.image.ImageMapper;
import com.poly.sof3021.ph29788.dto.request.image.ImageRequestDTO;
import com.poly.sof3021.ph29788.dto.response.image.ImageResponseDTO;
import com.poly.sof3021.ph29788.entities.image.Image;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.image.ImageRepository;
import com.poly.sof3021.ph29788.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Page<ImageResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Image> imagePage = imageRepository.findAll(pageable);
        return imagePage.map(ImageMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public ImageResponseDTO getById(Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return ImageMapper.INSTANCE.toResponseDTO(image);
        } else {
            throw new ResourceNotFoundException("Image with id " + id + " not found");
        }
    }

    @Override
    public ImageResponseDTO save(ImageRequestDTO requestDTO) {
        Image image = ImageMapper.INSTANCE.toEntity(requestDTO);
        return ImageMapper.INSTANCE.toResponseDTO(this.imageRepository.save(image));
    }

    @Override
    public ImageResponseDTO update(ImageRequestDTO requestDTO, Long id) {
        Optional<Image> checkExistingImage = imageRepository.findById(id);
        if (!checkExistingImage.isPresent()) {
            throw new ResourceNotFoundException("Image with id " + id + " Not Found");
        }

        Image existingImage = checkExistingImage.get();
        Image updatedImage = ImageMapper.INSTANCE.toEntity(requestDTO);
        updatedImage.setId(id);
        updatedImage.setCreatedAt(existingImage.getCreatedAt());

        updatedImage = imageRepository.save(updatedImage);
        return ImageMapper.INSTANCE.toResponseDTO(updatedImage);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
}
