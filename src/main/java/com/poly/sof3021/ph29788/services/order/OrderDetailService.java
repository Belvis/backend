package com.poly.sof3021.ph29788.services.order;

import com.poly.sof3021.ph29788.common.core.CrudService;
import com.poly.sof3021.ph29788.dto.request.order.OrderDetailRequestDTO;
import com.poly.sof3021.ph29788.dto.response.order.OrderDetailResponseDTO;
import org.springframework.data.domain.Page;

public interface OrderDetailService {

//    Page<OrderDetailResponseDTO> getAll(int page, int size, String sortField, String sortOrder);

    Page<OrderDetailResponseDTO> getAllByOrderId(Long orderId, int page, int size, String sortField, String sortOrder);

    OrderDetailResponseDTO getByOrder_IdAndId(Long orderId, Long OrderDetailId);

    OrderDetailResponseDTO save(Long orderId, OrderDetailRequestDTO requestDTO);

    OrderDetailResponseDTO update(Long orderId, Long OrderDetailId, OrderDetailRequestDTO requestDTO);

    void delete(Long orderId, Long OrderDetailId);

}
