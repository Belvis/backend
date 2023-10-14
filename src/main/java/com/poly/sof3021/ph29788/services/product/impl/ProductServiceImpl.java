package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.product.ProductMapper;
import com.poly.sof3021.ph29788.dto.request.product.ProductRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductResponseDTO;
import com.poly.sof3021.ph29788.entities.product.Product;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.ProductRepository;
import com.poly.sof3021.ph29788.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ProductMapper.INSTANCE.toResponseDTO(product);
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO requestDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(requestDTO);
        return ProductMapper.INSTANCE.toResponseDTO(this.productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(ProductRequestDTO requestDTO, Long id) {
        Optional<Product> checkExistingProduct = productRepository.findById(id);
        if (!checkExistingProduct.isPresent()) {
            throw new ResourceNotFoundException("Product with id " + id + " Not Found");
        }

        Product existingProduct = checkExistingProduct.get();
        Product updatedProduct = ProductMapper.INSTANCE.toEntity(requestDTO);
        updatedProduct.setId(id);
        updatedProduct.setCreatedAt(existingProduct.getCreatedAt());

        updatedProduct = productRepository.save(updatedProduct);
        return ProductMapper.INSTANCE.toResponseDTO(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
