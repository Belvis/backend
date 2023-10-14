package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.SizeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.SizeResponseDTO;
import com.poly.sof3021.ph29788.services.product.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sizes")
public class SizeRestController {

    private final SizeService sizeService;

    @Autowired
    public SizeRestController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<Page<SizeResponseDTO>> getAllSizes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<SizeResponseDTO> sizes = sizeService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeResponseDTO> findSizeById(@PathVariable("id") Long id) {
        SizeResponseDTO size = sizeService.getById(id);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SizeResponseDTO> saveSize(@Valid @RequestBody SizeRequestDTO sizeRequestDTO) {
        SizeResponseDTO responseDTO = sizeService.save(sizeRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeResponseDTO> updateSize(@Valid @RequestBody SizeRequestDTO sizeRequestDTO, @PathVariable("id") Long id) {
        SizeResponseDTO responseDTO = sizeService.update(sizeRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSize(@PathVariable("id") Long id) {
        sizeService.delete(id);
        return new ResponseEntity<>("Size deleted successfully", HttpStatus.OK);
    }
}
