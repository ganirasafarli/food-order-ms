package com.samganira.food_order_ms.controller;

import com.samganira.food_order_ms.mapper.Menu;
import com.samganira.food_order_ms.mapper.dto.OrderDTO;
import com.samganira.food_order_ms.mapper.response.OrderResponse;
import com.samganira.food_order_ms.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class FoodOrderController {
    private final FoodService foodService;

    @GetMapping("/show")
    public ResponseEntity<Menu> showMenu() {
        return ResponseEntity.ok(foodService.showMenu());
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> orderFood(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(foodService.orderFood(orderDTO));
    }
}