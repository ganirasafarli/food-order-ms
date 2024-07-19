package com.samganira.food_order_ms.service;

import com.samganira.food_order_ms.mapper.KafkaStatus;
import com.samganira.food_order_ms.mapper.OrderRecord;
import com.samganira.food_order_ms.mapper.dto.OrderDTO;
import com.samganira.food_order_ms.repository.KafkaRepository;
import com.samganira.food_order_ms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "food_order";
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaRepository kafkaRepository;
    private final OrderRepository orderRepository;

    public void sendFoodNames(OrderDTO order) {
        try {
            CompletableFuture<SendResult<String, String>> send = kafkaTemplate
                    .send(TOPIC, order.getFoodNames().toString());
            send.thenAccept(result -> {
                OrderRecord orderRecord = new OrderRecord()
                        .setStatus("IN PROGRESS")
                        .setFoodNames(order.getFoodNames().toString())
                        .setId(order.getUuid())
                        .setCanceled("NO")
                        .setUsername("TEST");
                orderRepository.save(orderRecord);
            }).exceptionally(ex -> {
                KafkaStatus kafkaStatus = new KafkaStatus()
                        .setEventId(order.getUuid())
                        .setStatus("FAILED")
                        .setFoodNames(order.getFoodNames().toString());
                kafkaRepository.save(kafkaStatus);
                return null;
            });
        } catch (Exception e) {
            System.err.println("Exception in sendFoodNames method: " + e.getMessage());
        }
    }

}

