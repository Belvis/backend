package com.poly.sof3021.ph29788.services.cart.impl;

import com.poly.sof3021.ph29788.dto.mapper.cart.CartMapper;
import com.poly.sof3021.ph29788.dto.request.cart.CartRequestDTO;
import com.poly.sof3021.ph29788.dto.response.cart.CartResponseDTO;
import com.poly.sof3021.ph29788.entities.cart.Cart;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.cart.CartRepository;
import com.poly.sof3021.ph29788.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Page<CartResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Cart> cartPage = cartRepository.findAll(pageable);
        return cartPage.map(CartMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public CartResponseDTO getById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return CartMapper.INSTANCE.toResponseDTO(cart);
        } else {
            throw new ResourceNotFoundException("Cart with id " + id + " not found");
        }
    }

    @Override
    public CartResponseDTO save(CartRequestDTO requestDTO) {
        Cart cart = CartMapper.INSTANCE.toEntity(requestDTO);
        return CartMapper.INSTANCE.toResponseDTO(this.cartRepository.save(cart));
    }

    @Override
    public CartResponseDTO update(CartRequestDTO requestDTO, Long id) {
        Optional<Cart> checkExistingCart = cartRepository.findById(id);
        if (!checkExistingCart.isPresent()) {
            throw new ResourceNotFoundException("Cart with id " + id + " Not Found");
        }

        Cart existingCart = checkExistingCart.get();
        Cart updatedCart = CartMapper.INSTANCE.toEntity(requestDTO);
        updatedCart.setId(id);
        updatedCart.setCreatedAt(existingCart.getCreatedAt());

        updatedCart = cartRepository.save(updatedCart);
        return CartMapper.INSTANCE.toResponseDTO(updatedCart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
