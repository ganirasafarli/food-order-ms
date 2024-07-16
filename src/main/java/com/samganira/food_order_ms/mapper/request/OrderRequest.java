package com.samganira.food_order_ms.mapper.request;

import com.samganira.food_order_ms.mapper.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

    @Getter
    @Setter
    public class OrderRequest {
        private List<String> names;
        private Payment payment;
    }
