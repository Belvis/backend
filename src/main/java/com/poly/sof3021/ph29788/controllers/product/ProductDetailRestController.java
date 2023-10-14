package com.poly.sof3021.ph29788.controllers.product;

import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderDetailResponseDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductResponseDTO;
import com.poly.sof3021.ph29788.services.product.ProductDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductDetailRestController {

    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailRestController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping("/all/details")
    public ResponseEntity<Page<ProductDetailResponseDTO>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<ProductDetailResponseDTO> productDetails = productDetailService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/all/details/{id}")
    public ResponseEntity<ProductDetailResponseDTO> findProductDetailById(
            @PathVariable("id") Long productDetailId
    ) {
        ProductDetailResponseDTO responseDTO = productDetailService.getById(productDetailId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{product_id}/details")
    public ResponseEntity<Page<ProductDetailResponseDTO>> getProductDetailByProductId(
            @PathVariable("product_id") Long productId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<ProductDetailResponseDTO> productDetails = productDetailService.getAllByProductId(productId, page, size, sortField, sortOrder);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/{product_id}/details/{id}")
    public ResponseEntity<ProductDetailResponseDTO> findProductDetailById(
            @PathVariable("product_id") Long productId,
            @PathVariable("id") Long productDetailId
            ) {
        ProductDetailResponseDTO responseDTO = productDetailService.getByProduct_IdAndId(productId, productDetailId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/{product_id}/details")
    public ResponseEntity<ProductDetailResponseDTO> saveProductDetail(
            @PathVariable("product_id") Long productId,
            @Valid @RequestBody ProductDetailRequestDTO productDetailRequestDTO
    ) {
        ProductDetailResponseDTO responseDTO = productDetailService.save(productId, productDetailRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{product_id}/details/{id}")
    public ResponseEntity<ProductDetailResponseDTO> updateProductDetail(
            @PathVariable("product_id") Long productId,
            @PathVariable("id") Long productDetailId,
            @Valid @RequestBody ProductDetailRequestDTO productDetailRequestDTO
    ) {
        ProductDetailResponseDTO responseDTO = productDetailService.update(productId, productDetailId, productDetailRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}/details/{id}")
    public ResponseEntity<String> deleteProductDetail(
            @PathVariable("product_id") Long productId,
            @PathVariable("id") Long productDetailId
            ) {
        productDetailService.delete(productId, productDetailId);
        return new ResponseEntity<>("Product Detail deleted successfully", HttpStatus.OK);
    }
}
