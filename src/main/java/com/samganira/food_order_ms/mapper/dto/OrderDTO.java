package com.samganira.food_order_ms.mapper.dto;

import com.samganira.food_order_ms.mapper.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDTO implements Serializable {
    private transient UUID uuid = UUID.randomUUID();
    private List<String> foodNames;
    private Payment payment;
}
