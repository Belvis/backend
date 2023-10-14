package com.poly.sof3021.ph29788.repositories.order;

import com.poly.sof3021.ph29788.entities.order.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Page<OrderDetail> findAllByOrder_Id(Long orderId, Pageable pageable);

}
