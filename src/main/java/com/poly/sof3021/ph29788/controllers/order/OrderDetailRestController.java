package com.poly.sof3021.ph29788.controllers.order;

import com.poly.sof3021.ph29788.dto.request.order.OrderDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.request.product.ProductDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderDetailResponseDTO;
import com.poly.sof3021.ph29788.dto.response.product.ProductDetailResponseDTO;
import com.poly.sof3021.ph29788.services.order.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderDetailRestController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailRestController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

//    @GetMapping("/api/v1/orders/all/details")
//    public ResponseEntity<Page<OrderDetailResponseDTO>> getAll(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "id") String sortField,
//            @RequestParam(defaultValue = "asc") String sortOrder
//    ) {
//        Page<OrderDetailResponseDTO> orderDetails = orderDetailService.getAll(page, size, sortField, sortOrder);
//        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//    }


    @GetMapping("/{order_id}/details")
    public ResponseEntity<Page<OrderDetailResponseDTO>> getAllOrderDetails(
            @PathVariable("order_id") Long orderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<OrderDetailResponseDTO> orderDetails = orderDetailService.getAllByOrderId(orderId, page, size, sortField, sortOrder);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{order_id}/details/{id}")
    public ResponseEntity<OrderDetailResponseDTO> findOrderDetailById(
            @PathVariable("order_id") Long orderId,
            @PathVariable("id") Long orderDetailId
    ) {
        OrderDetailResponseDTO responseDTO = orderDetailService.getByOrder_IdAndId(orderId, orderDetailId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/{order_id}/details")
    public ResponseEntity<OrderDetailResponseDTO> saveOrderDetail(
            @PathVariable("order_id") Long orderId,
            @Valid @RequestBody OrderDetailRequestDTO orderDetailRequestDTO
    ) {
        OrderDetailResponseDTO responseDTO = orderDetailService.save(orderId, orderDetailRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{order_id}/details/{id}")
    public ResponseEntity<OrderDetailResponseDTO> updateOrderDetail(
            @PathVariable("order_id") Long orderId,
            @PathVariable("id") Long orderDetailId,
            @Valid @RequestBody OrderDetailRequestDTO orderDetailRequestDTO
    ) {
        OrderDetailResponseDTO responseDTO = orderDetailService.update(orderId, orderDetailId, orderDetailRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{order_id}/details/{id}")
    public ResponseEntity<String> deleteProductDetail(
            @PathVariable("order_id") Long orderId,
            @PathVariable("id") Long orderDetailId
    ) {
        orderDetailService.delete(orderId, orderDetailId);
        return new ResponseEntity<>("Order Detail deleted successfully", HttpStatus.OK);
    }
}
