package com.samganira.food_order_ms.repository;

import com.samganira.food_order_ms.mapper.KafkaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KafkaRepository extends JpaRepository<KafkaStatus, UUID> {

}
