package com.poly.sof3021.ph29788.services.order.impl;

import com.poly.sof3021.ph29788.dto.mapper.order.OrderDetailMapper;
import com.poly.sof3021.ph29788.dto.mapper.product.ProductDetailMapper;
import com.poly.sof3021.ph29788.dto.request.order.OrderDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.ColorRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.MaterialRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.SizeRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.StyleRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderDetailResponseDTO;
import com.poly.sof3021.ph29788.entities.order.Order;
import com.poly.sof3021.ph29788.entities.order.OrderDetail;
import com.poly.sof3021.ph29788.entities.product.Product;
import com.poly.sof3021.ph29788.entities.product.ProductDetail;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.order.OrderDetailRepository;
import com.poly.sof3021.ph29788.repositories.order.OrderRepository;
import com.poly.sof3021.ph29788.repositories.product.ProductDetailRepository;
import com.poly.sof3021.ph29788.services.order.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductDetailRepository productDetailRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderDetailServiceImpl(
            OrderDetailRepository orderDetailRepository,
            ProductDetailRepository productDetailRepository,
            OrderRepository orderRepository
    ) {
        this.orderDetailRepository = orderDetailRepository;
        this.productDetailRepository = productDetailRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<OrderDetailResponseDTO> getAllByOrderId(Long orderId, int page, int size, String sortField, String sortOrder) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            Pageable pageable = PageRequest.of(page - 1, size, sort);
            Page<OrderDetail> orderDetailPage = orderDetailRepository.findAllByOrder_Id(orderId, pageable);
            return orderDetailPage.map(OrderDetailMapper.INSTANCE::toResponseDTO);
        }
        throw new ResourceNotFoundException("Order not found with ID: " + orderId);
    }

    @Override
    public OrderDetailResponseDTO getByOrder_IdAndId(Long orderId, Long orderDetailId) {
        OrderDetail orderDetail = validateOrderDetail(orderId, orderDetailId);
        return OrderDetailMapper.INSTANCE.toResponseDTO(orderDetail);
    }

    @Override
    public OrderDetailResponseDTO save(Long orderId, OrderDetailRequestDTO requestDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        OrderDetail orderDetail = OrderDetailMapper.INSTANCE.toEntity(requestDTO);
        orderDetail.setOrder(order);
        updateOrderDetailFields(orderDetail, requestDTO);

        return OrderDetailMapper.INSTANCE.toResponseDTO(this.orderDetailRepository.save(orderDetail));
    }

    @Override
    public OrderDetailResponseDTO update(Long orderId, Long orderDetailId, OrderDetailRequestDTO requestDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail not found with ID: " + orderDetailId));

        OrderDetail updatedOrderDetail = OrderDetailMapper.INSTANCE.toEntity(requestDTO);
        updatedOrderDetail.setOrder(order);
        updatedOrderDetail.setId(orderDetailId);
        updatedOrderDetail.setCreatedAt(existingOrderDetail.getCreatedAt());
        updateOrderDetailFields(updatedOrderDetail, requestDTO);

        return OrderDetailMapper.INSTANCE.toResponseDTO(this.orderDetailRepository.save(updatedOrderDetail));
    }

    @Override
    public void delete(Long orderId, Long OrderDetailId) {
        OrderDetail orderDetail = validateOrderDetail(orderId, OrderDetailId);
        orderDetailRepository.delete(orderDetail);
    }

    private OrderDetail validateOrderDetail(Long orderId, Long orderDetailId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Detail not found with ID: " + orderDetailId));

        if (!orderDetail.getOrder().equals(order)) {
            throw new IllegalArgumentException("Order Detail does not belong to the specified Order");
        }

        return orderDetail;
    }

    private void updateOrderDetailFields(OrderDetail orderDetail, OrderDetailRequestDTO requestDTO) {
        Optional.ofNullable(requestDTO.getProductDetail())
                .map(ProductDetailRequestDTO::getId)
                .flatMap(productDetailRepository::findById)
                .ifPresent(orderDetail::setProductDetail);
    }

}
