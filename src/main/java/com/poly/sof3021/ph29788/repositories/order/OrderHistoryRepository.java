package com.poly.sof3021.ph29788.repositories.order;

import com.poly.sof3021.ph29788.entities.order.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
