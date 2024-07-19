package com.samganira.food_order_ms.mapper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class OrderRecord {
    @Id
    private UUID id;
    private String username;
    private String foodNames;
    private String status;
    private String canceled;
}
