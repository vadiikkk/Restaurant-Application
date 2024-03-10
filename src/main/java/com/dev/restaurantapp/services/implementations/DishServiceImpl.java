package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.enumerable.DishStatus;
import com.dev.restaurantapp.exceptions.NameAlreadyExistsException;
import com.dev.restaurantapp.exceptions.NoSuchItemInDatabaseException;
import com.dev.restaurantapp.exceptions.RawCannotBeNullException;
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
        try {
            return dishRepository.save(dish);
        } catch (IllegalArgumentException exception) {
            throw new RawCannotBeNullException(exception.getMessage());
        } catch (RuntimeException exception) {
            throw new NameAlreadyExistsException(exception.getMessage());
        }
    }

    @Override
    public Dish updateDish(Dish dish) {
        Optional<Dish> toChange = dishRepository.findById(dish.getId());

        if (toChange.isEmpty()) {
            throw new NoSuchItemInDatabaseException("No value present");
        }

        try {
            return dishRepository.save(Dish.builder()
                    .id(toChange.get().getId())
                    .name(dish.getName())
                    .quantity(dish.getQuantity())
                    .price(dish.getPrice())
                    .dishStatus(dish.getDishStatus())
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new RawCannotBeNullException(exception.getMessage());
        } catch (RuntimeException exception) {
            throw new NameAlreadyExistsException(exception.getMessage());
        }

    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
