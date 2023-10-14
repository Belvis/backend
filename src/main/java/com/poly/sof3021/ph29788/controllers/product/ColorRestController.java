package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.ColorRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ColorResponseDTO;
import com.poly.sof3021.ph29788.services.product.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/colors")
public class ColorRestController {

    private final ColorService colorService;

    @Autowired
    public ColorRestController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<Page<ColorResponseDTO>> getAllColors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<ColorResponseDTO> colors = colorService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorResponseDTO> findColorById(@PathVariable("id") Long id) {
        ColorResponseDTO color = colorService.getById(id);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorResponseDTO> saveColor(@Valid @RequestBody ColorRequestDTO colorRequestDTO) {
        ColorResponseDTO responseDTO = colorService.save(colorRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorResponseDTO> updateColor(@Valid @RequestBody ColorRequestDTO colorRequestDTO, @PathVariable("id") Long id) {
        ColorResponseDTO responseDTO = colorService.update(colorRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteColor(@PathVariable("id") Long id) {
        colorService.delete(id);
        return new ResponseEntity<>("Color deleted successfully", HttpStatus.OK);
    }
}
