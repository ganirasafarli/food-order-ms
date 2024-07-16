package com.samganira.food_order_ms.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "menu")
public class Menu {
    @Id
    private String id;

    @Field("burgers")
    private List<Item> burgers;

    private Date date;

    @Field("drinks")
    private List<Item> drinks;

    @Field("salads")
    private List<Item> salads;

    private String title;

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Item {
        private String name;
        private String type;
        private double price;
    }
}
