package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.BrandResponseDTO;
import com.poly.sof3021.ph29788.services.product.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<BrandResponseDTO>> getAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<BrandResponseDTO> brands = this.brandService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> findUserById(@PathVariable("id") Long id) {
        BrandResponseDTO brand = this.brandService.getById(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandResponseDTO> saveUser(@Valid @RequestBody BrandRequestDTO brandRequestDTO) {
        BrandResponseDTO responseDTO = brandService.save(brandRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDTO> updateUser(@Valid @RequestBody BrandRequestDTO brandRequestDTO, @PathVariable("id") Long id) {
        BrandResponseDTO responseDTO = brandService.update(brandRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.brandService.delete(id);
        return new ResponseEntity<>("Brand deleted successfully", HttpStatus.OK);
    }

}
