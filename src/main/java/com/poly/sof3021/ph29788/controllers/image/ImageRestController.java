package com.poly.sof3021.ph29788.controllers.image;

import com.poly.sof3021.ph29788.dto.request.image.ImageRequestDTO;
import com.poly.sof3021.ph29788.dto.response.image.ImageResponseDTO;
import com.poly.sof3021.ph29788.services.image.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
public class ImageRestController {

    private final ImageService imageService;

    @Autowired
    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Page<ImageResponseDTO>> getAllImages(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<ImageResponseDTO> images = imageService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponseDTO> findImageById(@PathVariable("id") Long id) {
        ImageResponseDTO image = imageService.getById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImageResponseDTO> saveImage(@Valid @RequestBody ImageRequestDTO imageRequestDTO) {
        ImageResponseDTO responseDTO = imageService.save(imageRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageResponseDTO> updateImage(@Valid @RequestBody ImageRequestDTO imageRequestDTO, @PathVariable("id") Long id) {
        ImageResponseDTO responseDTO = imageService.update(imageRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") Long id) {
        imageService.delete(id);
        return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
    }
}
