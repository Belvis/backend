package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.StyleRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.StyleResponseDTO;
import com.poly.sof3021.ph29788.services.product.StyleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/styles")
public class StyleRestController {

    private final StyleService styleService;

    @Autowired
    public StyleRestController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping
    public ResponseEntity<Page<StyleResponseDTO>> getAllStyles(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<StyleResponseDTO> styles = styleService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StyleResponseDTO> findStyleById(@PathVariable("id") Long id) {
        StyleResponseDTO style = styleService.getById(id);
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StyleResponseDTO> saveStyle(@Valid @RequestBody StyleRequestDTO styleRequestDTO) {
        StyleResponseDTO responseDTO = styleService.save(styleRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StyleResponseDTO> updateStyle(@Valid @RequestBody StyleRequestDTO styleRequestDTO, @PathVariable("id") Long id) {
        StyleResponseDTO responseDTO = styleService.update(styleRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStyle(@PathVariable("id") Long id) {
        styleService.delete(id);
        return new ResponseEntity<>("Style deleted successfully", HttpStatus.OK);
    }
}
