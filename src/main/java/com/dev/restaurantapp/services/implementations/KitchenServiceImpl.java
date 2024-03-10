package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.entities.Order;
import com.dev.restaurantapp.enumerable.OrderPriority;
import com.dev.restaurantapp.enumerable.OrderStatus;
import com.dev.restaurantapp.exceptions.NoSuchItemInDatabaseException;
import com.dev.restaurantapp.repositories.DishRepository;
import com.dev.restaurantapp.repositories.OrderRepository;
import com.dev.restaurantapp.services.interfaces.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class KitchenServiceImpl implements KitchenService {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;

    @Override
    public void cook(Order order) throws InterruptedException {
        double maxTime = 0;
        for (Long dishId : order.getDishes()) {
            Optional<Dish> dishOpt = dishRepository.findById(dishId);
            if (dishOpt.isEmpty()) {
                throw new NoSuchItemInDatabaseException("No value present");
            }
            Dish dish = dishOpt.get();
            dish.setQuantity(dish.getQuantity() - 1);
            dishRepository.save(dish);
            maxTime = dish.getTimeToCook() > maxTime ? dish.getTimeToCook() : maxTime;
        }

        if (order.getPriority().equals(OrderPriority.HIGH)) {
            maxTime *= 0.7;
        }

        Thread.sleep((long) maxTime * 1000L);
        order.setStatus(OrderStatus.DONE);
        orderRepository.save(order);
    }
}
