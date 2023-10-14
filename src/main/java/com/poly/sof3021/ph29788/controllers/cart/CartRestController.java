package com.poly.sof3021.ph29788.controllers.cart;

import com.poly.sof3021.ph29788.dto.request.cart.CartRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartResponseDTO;
import com.poly.sof3021.ph29788.services.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestController {

    private final CartService cartService;

    @Autowired
    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Page<CartResponseDTO>> getAllCarts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<CartResponseDTO> carts = cartService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> findCartById(@PathVariable("id") Long id) {
        CartResponseDTO cart = cartService.getById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> saveCart(@Valid @RequestBody CartRequestDTO cartRequestDTO) {
        CartResponseDTO responseDTO = cartService.save(cartRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDTO> updateCart(@Valid @RequestBody CartRequestDTO cartRequestDTO, @PathVariable("id") Long id) {
        CartResponseDTO responseDTO = cartService.update(cartRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long id) {
        cartService.delete(id);
        return new ResponseEntity<>("Cart deleted successfully", HttpStatus.OK);
    }
}
