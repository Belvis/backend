package com.poly.sof3021.ph29788.controllers.order;

import com.poly.sof3021.ph29788.dto.request.order.OrderHistoryRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderHistoryResponseDTO;
import com.poly.sof3021.ph29788.services.order.OrderHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-histories")
public class OrderHistoryRestController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryRestController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderHistoryResponseDTO>> getAllOrderHistories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<OrderHistoryResponseDTO> orderHistories = orderHistoryService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(orderHistories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHistoryResponseDTO> findOrderHistoryById(@PathVariable("id") Long id) {
        OrderHistoryResponseDTO orderHistory = orderHistoryService.getById(id);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderHistoryResponseDTO> saveOrderHistory(@Valid @RequestBody OrderHistoryRequestDTO orderHistoryRequestDTO) {
        OrderHistoryResponseDTO responseDTO = orderHistoryService.save(orderHistoryRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHistoryResponseDTO> updateOrderHistory(@Valid @RequestBody OrderHistoryRequestDTO orderHistoryRequestDTO, @PathVariable("id") Long id) {
        OrderHistoryResponseDTO responseDTO = orderHistoryService.update(orderHistoryRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderHistory(@PathVariable("id") Long id) {
        orderHistoryService.delete(id);
        return new ResponseEntity<>("OrderHistory deleted successfully", HttpStatus.OK);
    }
}
