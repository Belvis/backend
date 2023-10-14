package com.poly.sof3021.ph29788.controllers.order;

import com.poly.sof3021.ph29788.dto.request.order.OrderRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderResponseDTO;
import com.poly.sof3021.ph29788.services.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<OrderResponseDTO> orders = orderService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable("id") Long id) {
        OrderResponseDTO order = orderService.getById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> saveOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO responseDTO = orderService.save(orderRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO, @PathVariable("id") Long id) {
        OrderResponseDTO responseDTO = orderService.update(orderRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}
