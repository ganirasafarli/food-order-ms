package com.samganira.food_order_ms.mapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class KafkaStatus {
    @Id
    private UUID eventId;
    private String status;
    private String foodNames;
}
