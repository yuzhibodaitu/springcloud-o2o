package com.dt.order.repository;

import com.dt.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail ,String> {
}
