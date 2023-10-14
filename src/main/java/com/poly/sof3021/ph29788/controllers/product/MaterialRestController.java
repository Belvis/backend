package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.MaterialRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.MaterialResponseDTO;
import com.poly.sof3021.ph29788.services.product.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialRestController {

    private final MaterialService materialService;

    @Autowired
    public MaterialRestController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<Page<MaterialResponseDTO>> getAllMaterials(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<MaterialResponseDTO> materials = materialService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> findMaterialById(@PathVariable("id") Long id) {
        MaterialResponseDTO material = materialService.getById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> saveMaterial(@Valid @RequestBody MaterialRequestDTO materialRequestDTO) {
        MaterialResponseDTO responseDTO = materialService.save(materialRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> updateMaterial(@Valid @RequestBody MaterialRequestDTO materialRequestDTO, @PathVariable("id") Long id) {
        MaterialResponseDTO responseDTO = materialService.update(materialRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable("id") Long id) {
        materialService.delete(id);
        return new ResponseEntity<>("Material deleted successfully", HttpStatus.OK);
    }
}
