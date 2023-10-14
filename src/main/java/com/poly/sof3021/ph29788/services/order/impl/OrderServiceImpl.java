package com.poly.sof3021.ph29788.services.order.impl;

import com.poly.sof3021.ph29788.dto.mapper.order.OrderMapper;
import com.poly.sof3021.ph29788.dto.request.order.OrderRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.BrandRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.AddressRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.CustomerRequestDTO;
import com.poly.sof3021.ph29788.dto.request.user.EmployeeRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderResponseDTO;
import com.poly.sof3021.ph29788.entities.order.Order;
import com.poly.sof3021.ph29788.infrastructure.exceptions.ResourceNotFoundException;
import com.poly.sof3021.ph29788.repositories.order.OrderRepository;
import com.poly.sof3021.ph29788.repositories.user.AddressRepository;
import com.poly.sof3021.ph29788.repositories.user.CustomerRepository;
import com.poly.sof3021.ph29788.repositories.user.EmployeeRepository;
import com.poly.sof3021.ph29788.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            EmployeeRepository employeeRepository
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<OrderResponseDTO> getAll(int page, int size, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(OrderMapper.INSTANCE::toResponseDTO);
    }

    @Override
    public OrderResponseDTO getById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            return OrderMapper.INSTANCE.toResponseDTO(order);
        } else {
            throw new ResourceNotFoundException("Order with id " + id + " not found");
        }
    }

    @Override
    public OrderResponseDTO save(OrderRequestDTO requestDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(requestDTO);
        updateOrderFields(order, requestDTO);

        return OrderMapper.INSTANCE.toResponseDTO(this.orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO update(OrderRequestDTO requestDTO, Long id) {
        Optional<Order> checkExistingOrder = orderRepository.findById(id);
        if (!checkExistingOrder.isPresent()) {
            throw new ResourceNotFoundException("Order with id " + id + " Not Found");
        }

        Order existingOrder = checkExistingOrder.get();
        Order updatedOrder = OrderMapper.INSTANCE.toEntity(requestDTO);
        updatedOrder.setId(id);
        updatedOrder.setCreatedAt(existingOrder.getCreatedAt());
        updateOrderFields(updatedOrder, requestDTO);

        return OrderMapper.INSTANCE.toResponseDTO(this.orderRepository.save(updatedOrder));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private void updateOrderFields(Order order, OrderRequestDTO requestDTO) {
        Optional.ofNullable(requestDTO.getCustomer())
                .map(CustomerRequestDTO::getId)
                .flatMap(customerRepository::findById)
                .ifPresent(order::setCustomer);

        Optional.ofNullable(requestDTO.getEmployee())
                .map(EmployeeRequestDTO::getId)
                .flatMap(employeeRepository::findById)
                .ifPresent(order::setEmployee);

        Optional.ofNullable(requestDTO.getAddress())
                .map(AddressRequestDTO::getId)
                .flatMap(addressRepository::findById)
                .ifPresent(order::setAddress);
    }
}
