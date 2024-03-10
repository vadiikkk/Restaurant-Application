package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.enumerable.DishStatus;
import com.dev.restaurantapp.repositories.DishRepository;
import com.dev.restaurantapp.services.interfaces.DishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<Dish> getActualMenu() {
        return dishRepository.findByDishStatusAndQuantityGreaterThan(DishStatus.OK, 0);
    }

    @Override
    public List<Dish> getFullMenu() {
        return dishRepository.findAll();
    }

    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Dish dish) {
        return dishRepository.save(dish); // TODO
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
