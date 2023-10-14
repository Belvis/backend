package com.poly.sof3021.ph29788.services.cart.impl;

import com.poly.sof3021.ph29788.dto.mapper.cart.CartDetailMapper;
import com.poly.sof3021.ph29788.dto.request.cart.CartDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.cart.CartDetail;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.cart.CartDetailRepository;
import com.poly.sof3021.ph29788.services.cart.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public Page<CartDetailResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<CartDetail> cartDetailPage = cartDetailRepository.findAll(pageable);
        return cartDetailPage.map(CartDetailMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public CartDetailResponseDTO getById(Long id) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById(id);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();
            return CartDetailMapper.INSTANCE.toResponseDTO(cartDetail);
        } else {
            throw new ResourceNotFoundException("CartDetail with id " + id + " not found");
        }
    }

    @Override
    public CartDetailResponseDTO save(CartDetailRequestDTO requestDTO) {
        CartDetail cartDetail = CartDetailMapper.INSTANCE.toEntity(requestDTO);
        return CartDetailMapper.INSTANCE.toResponseDTO(this.cartDetailRepository.save(cartDetail));
    }

    @Override
    public CartDetailResponseDTO update(CartDetailRequestDTO requestDTO, Long id) {
        Optional<CartDetail> checkExistingCartDetail = cartDetailRepository.findById(id);
        if (!checkExistingCartDetail.isPresent()) {
            throw new ResourceNotFoundException("CartDetail with id " + id + " Not Found");
        }

        CartDetail existingCartDetail = checkExistingCartDetail.get();
        CartDetail cartDetail = CartDetailMapper.INSTANCE.toEntity(requestDTO);
        cartDetail.setId(id);
        cartDetail.setCreatedAt(existingCartDetail.getCreatedAt());
        cartDetail = cartDetailRepository.save(cartDetail);
        return CartDetailMapper.INSTANCE.toResponseDTO(cartDetail);
    }

    @Override
    public void delete(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
