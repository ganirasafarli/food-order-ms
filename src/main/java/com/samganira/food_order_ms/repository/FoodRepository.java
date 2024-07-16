package com.samganira.food_order_ms.repository;

import com.samganira.food_order_ms.mapper.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Menu, String> {
    @Query("{}")
    Menu showMenu();
}
