package com.samganira.food_order_ms.service;

import com.samganira.food_order_ms.mapper.Menu;
import com.samganira.food_order_ms.mapper.entity.Payment;
import com.samganira.food_order_ms.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public boolean nonExistCard() {
        return paymentRepository.count() == 0;
    }

    public void save(List<Payment> cards) {
        paymentRepository.saveAll(cards);
    }
}
