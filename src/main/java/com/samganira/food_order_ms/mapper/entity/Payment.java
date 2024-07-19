package com.samganira.food_order_ms.mapper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "card_info")
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "card_number")
    private String cardNumber;
    private Double balance;
}

