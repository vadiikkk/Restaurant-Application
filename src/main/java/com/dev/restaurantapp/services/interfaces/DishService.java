package com.dev.restaurantapp.services.interfaces;

import com.dev.restaurantapp.entities.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<Dish> getActualMenu();

    List<Dish> getFullMenu();

    Dish addDish(Dish dish);

    Dish updateDish(Dish dish);

    void deleteDish(Long id);
}
