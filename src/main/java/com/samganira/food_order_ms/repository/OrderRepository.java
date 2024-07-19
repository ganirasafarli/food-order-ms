package com.samganira.food_order_ms.repository;

import com.samganira.food_order_ms.mapper.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderRecord, Long> {
}
