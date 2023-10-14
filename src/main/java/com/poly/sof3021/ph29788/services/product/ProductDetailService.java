package com.poly.sof3021.ph29788.services.product;

import com.poly.sof3021.ph29788.common.core.CrudService;
import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductDetailService {

    Page<ProductDetailResponseDTO> getAll(int page, int size, String sortField, String sortOrder);

    Page<ProductDetailResponseDTO> getAllByProductId(Long productId, int page, int size, String sortField, String sortOrder);

    ProductDetailResponseDTO getByProduct_IdAndId(Long productId, Long productDetailId);

    ProductDetailResponseDTO getById(Long productDetailId);

    ProductDetailResponseDTO save(Long productId, ProductDetailRequestDTO requestDTO);

    ProductDetailResponseDTO update(Long productId, Long productDetailId, ProductDetailRequestDTO requestDTO);

    void delete(Long productId, Long productDetailId);

}
