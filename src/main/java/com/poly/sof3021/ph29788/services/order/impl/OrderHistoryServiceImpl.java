package com.poly.sof3021.ph29788.services.order.impl;

import com.poly.sof3021.ph29788.dto.mapper.order.OrderHistoryMapper;
import com.poly.sof3021.ph29788.dto.request.order.OrderHistoryRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderHistoryResponseDTO;
import com.poly.sof3021.ph29788.entities.order.OrderHistory;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.order.OrderHistoryRepository;
import com.poly.sof3021.ph29788.services.order.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public Page<OrderHistoryResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<OrderHistory> orderHistoryPage = orderHistoryRepository.findAll(pageable);
        return orderHistoryPage.map(OrderHistoryMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public OrderHistoryResponseDTO getById(Long id) {
        Optional<OrderHistory> orderHistoryOptional = orderHistoryRepository.findById(id);
        if (orderHistoryOptional.isPresent()) {
            OrderHistory orderHistory = orderHistoryOptional.get();
            return OrderHistoryMapper.INSTANCE.toResponseDTO(orderHistory);
        } else {
            throw new ResourceNotFoundException("OrderHistory with id " + id + " not found");
        }
    }

    @Override
    public OrderHistoryResponseDTO save(OrderHistoryRequestDTO requestDTO) {
        OrderHistory orderHistory = OrderHistoryMapper.INSTANCE.toEntity(requestDTO);
        return OrderHistoryMapper.INSTANCE.toResponseDTO(this.orderHistoryRepository.save(orderHistory));
    }

    @Override
    public OrderHistoryResponseDTO update(OrderHistoryRequestDTO requestDTO, Long id) {
        Optional<OrderHistory> checkExistingOrderHistory = orderHistoryRepository.findById(id);
        if (!checkExistingOrderHistory.isPresent()) {
            throw new ResourceNotFoundException("OrderHistory with id " + id + " Not Found");
        }

        OrderHistory existingOrderHistory = checkExistingOrderHistory.get();
        OrderHistory updatedOrderHistory = OrderHistoryMapper.INSTANCE.toEntity(requestDTO);
        updatedOrderHistory.setId(id);
        updatedOrderHistory.setCreatedAt(existingOrderHistory.getCreatedAt());

        updatedOrderHistory = orderHistoryRepository.save(updatedOrderHistory);
        return OrderHistoryMapper.INSTANCE.toResponseDTO(updatedOrderHistory);
    }

    @Override
    public void delete(Long id) {
        orderHistoryRepository.deleteById(id);
    }
}
