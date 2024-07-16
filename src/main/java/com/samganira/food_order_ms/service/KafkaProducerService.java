package com.samganira.food_order_ms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "food_order";
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendFoodNames(String foodNames) {
        kafkaTemplate.send(TOPIC, foodNames);
    }
}
