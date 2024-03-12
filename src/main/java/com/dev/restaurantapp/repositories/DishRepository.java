package com.dev.restaurantapp.repositories;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.enumerable.DishStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByDishStatusAndQuantityGreaterThan(DishStatus dishStatus, int quantity);
}
