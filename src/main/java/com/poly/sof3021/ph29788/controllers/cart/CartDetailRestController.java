package com.poly.sof3021.ph29788.controllers.cart;

import com.poly.sof3021.ph29788.dto.request.cart.CartDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartDetailResponseDTO;
import com.poly.sof3021.ph29788.services.cart.CartDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-details")
public class CartDetailRestController {

    private final CartDetailService cartDetailService;

    @Autowired
    public CartDetailRestController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @GetMapping
    public ResponseEntity<Page<CartDetailResponseDTO>> getAllCartDetails(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<CartDetailResponseDTO> cartDetails = cartDetailService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDetailResponseDTO> findCartDetailById(@PathVariable("id") Long id) {
        CartDetailResponseDTO cartDetail = cartDetailService.getById(id);
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartDetailResponseDTO> saveCartDetail(@Valid @RequestBody CartDetailRequestDTO cartDetailRequestDTO) {
        CartDetailResponseDTO responseDTO = cartDetailService.save(cartDetailRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDetailResponseDTO> updateCartDetail(@Valid @RequestBody CartDetailRequestDTO cartDetailRequestDTO, @PathVariable("id") Long id) {
        CartDetailResponseDTO responseDTO = cartDetailService.update(cartDetailRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartDetail(@PathVariable("id") Long id) {
        cartDetailService.delete(id);
        return new ResponseEntity<>("CartDetail deleted successfully", HttpStatus.OK);
    }
}
