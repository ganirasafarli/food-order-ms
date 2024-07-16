package com.samganira.food_order_ms.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samganira.food_order_ms.mapper.Menu;
import com.samganira.food_order_ms.mapper.entity.Payment;
import com.samganira.food_order_ms.service.FoodService;
import com.samganira.food_order_ms.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final ObjectMapper mapper;
    private final FoodService foodService;
    private final PaymentService paymentService;

    @Bean
    CommandLineRunner loadMenu() {
        return args -> {
            if (foodService.nonExistMenu()) {
                TypeReference<Menu> typeReference = new TypeReference<>() {
                };
                InputStream inputStream = TypeReference.class.getClassLoader().getResourceAsStream("initial-menu-data.json");
                Menu menu = mapper.readValue(inputStream, typeReference);
                foodService.save(menu);
            }
        };
    }

    @Bean
    CommandLineRunner loadCardInfo() {
        return args -> {
            if (paymentService.nonExistCard()) {
                TypeReference<List<Payment>> typeReference = new TypeReference<>() {
                };
                InputStream inputStream = TypeReference.class.getClassLoader().getResourceAsStream("initial-card-data.json");
                List<Payment> payment = mapper.readValue(inputStream, typeReference);
                paymentService.save(payment);
            }
        };
    }


}
