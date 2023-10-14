package com.poly.sof3021.ph29788.services.product.impl;

import com.poly.sof3021.ph29788.dto.mapper.image.ImageMapper;
import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.ColorRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.MaterialRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.SizeRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.StyleRequestDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.image.Image;
import com.poly.sof3021.ph29788.entities.product.Brand;
import com.poly.sof3021.ph29788.entities.product.Color;
import com.poly.sof3021.ph29788.entities.product.Material;
import com.poly.sof3021.ph29788.entities.product.Product;
import com.poly.sof3021.ph29788.entities.product.ProductDetail;
import com.poly.sof3021.ph29788.entities.product.Size;
import com.poly.sof3021.ph29788.entities.product.Style;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.product.BrandRepository;
import com.poly.sof3021.ph29788.repositories.product.ColorRepository;
import com.poly.sof3021.ph29788.repositories.product.MaterialRepository;
import com.poly.sof3021.ph29788.repositories.product.ProductDetailRepository;
import com.poly.sof3021.ph29788.repositories.product.ProductRepository;
import com.poly.sof3021.ph29788.repositories.product.SizeRepository;
import com.poly.sof3021.ph29788.repositories.product.StyleRepository;
import com.poly.sof3021.ph29788.services.product.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final BrandRepository brandRepository;
    private final MaterialRepository materialRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final StyleRepository styleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductDetailServiceImpl(
            ProductDetailRepository productDetailRepository,
            BrandRepository brandRepository,
            MaterialRepository materialRepository,
            ColorRepository colorRepository,
            SizeRepository sizeRepository,
            StyleRepository styleRepository,
            ProductRepository productRepository
    ) {
        this.productDetailRepository = productDetailRepository;
        this.brandRepository = brandRepository;
        this.materialRepository = materialRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.styleRepository = styleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDetailResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProductDetail> productDetailPage = productDetailRepository.findAll(pageable);
        return productDetailPage.map(ProductDetailMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public Page<ProductDetailResponseDTO> getAllByProductId(Long productId, int page, int size, String sortField, String sortOrder) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            Page<ProductDetail> productDetailPage = productDetailRepository.findByProduct_Id(productId, pageable);
            return productDetailPage.map(ProductDetailMapper.INSTANCE::toResponseDTO);
        }
        throw new ResourceNotFoundException("Product not found with ID: " + productId);
    }

    @Override
    public ProductDetailResponseDTO getByProduct_IdAndId(Long productId, Long productDetailId) {
        ProductDetail productDetail = validateProductDetail(productId, productDetailId);
        return ProductDetailMapper.INSTANCE.toResponseDTO(productDetail);
    }

    @Override
    public ProductDetailResponseDTO getById(Long productDetailId) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(productDetailId);
        if (productDetailOptional.isPresent()) {
            ProductDetail productDetail = productDetailOptional.get();
            return ProductDetailMapper.INSTANCE.toResponseDTO(productDetail);
        } else {
            throw new ResourceNotFoundException("Product detail with id " + productDetailId + " not found");
        }
    }

    @Override
    public ProductDetailResponseDTO save(Long productId, ProductDetailRequestDTO requestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        ProductDetail productDetail = ProductDetailMapper.INSTANCE.toEntity(requestDTO);
        productDetail.setProduct(product);
        updateProductDetailFields(productDetail, requestDTO);

        return ProductDetailMapper.INSTANCE.toResponseDTO(this.productDetailRepository.save(productDetail));
    }


    @Override
    public ProductDetailResponseDTO update(Long productId, Long productDetailId, ProductDetailRequestDTO requestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        ProductDetail existingProductDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Detail not found with ID: " + productDetailId));

        ProductDetail updatedProductDetail = ProductDetailMapper.INSTANCE.toEntity(requestDTO);
        updatedProductDetail.setProduct(product);
        updatedProductDetail.setId(productDetailId);
        updatedProductDetail.setCreatedAt(existingProductDetail.getCreatedAt());
        updateProductDetailFields(updatedProductDetail, requestDTO);

        return ProductDetailMapper.INSTANCE.toResponseDTO(this.productDetailRepository.save(updatedProductDetail));
    }


    @Override
    public void delete(Long productId, Long productDetailId) {
        ProductDetail productDetail = validateProductDetail(productId, productDetailId);
        productDetailRepository.delete(productDetail);
    }

    private ProductDetail validateProductDetail(Long productId, Long productDetailId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        ProductDetail productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Detail not found with ID: " + productDetailId));

        if (!productDetail.getProduct().equals(product)) {
            throw new IllegalArgumentException("Product Detail does not belong to the specified Product");
        }

        return productDetail;
    }

    private void updateProductDetailFields(ProductDetail productDetail, ProductDetailRequestDTO requestDTO) {
        Optional.ofNullable(requestDTO.getBrand())
                .map(BrandRequestDTO::getId)
                .flatMap(brandRepository::findById)
                .ifPresent(productDetail::setBrand);

        Optional.ofNullable(requestDTO.getColor())
                .map(ColorRequestDTO::getId)
                .flatMap(colorRepository::findById)
                .ifPresent(productDetail::setColor);

        Optional.ofNullable(requestDTO.getMaterial())
                .map(MaterialRequestDTO::getId)
                .flatMap(materialRepository::findById)
                .ifPresent(productDetail::setMaterial);

        Optional.ofNullable(requestDTO.getSize())
                .map(SizeRequestDTO::getId)
                .flatMap(sizeRepository::findById)
                .ifPresent(productDetail::setSize);

        Optional.ofNullable(requestDTO.getStyle())
                .map(StyleRequestDTO::getId)
                .flatMap(styleRepository::findById)
                .ifPresent(productDetail::setStyle);
    }

}
