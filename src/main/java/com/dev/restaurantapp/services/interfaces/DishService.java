package com.dev.restaurantapp.services.interfaces;

import com.dev.restaurantapp.entities.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getActualMenu();

    List<Dish> getFullMenu();

    Dish findById(Long Id);

    Dish addDish(Dish dish); // TODO dto

    Dish updateDish(Dish dish); // TODO dto

    void deleteDish(Long id);
}
