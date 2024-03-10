package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.dto.AddOrderRequest;
import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.entities.Order;
import com.dev.restaurantapp.enumerable.OrderPriority;
import com.dev.restaurantapp.enumerable.OrderStatus;
import com.dev.restaurantapp.exceptions.*;
import com.dev.restaurantapp.repositories.DishRepository;
import com.dev.restaurantapp.repositories.OrderRepository;
import com.dev.restaurantapp.services.interfaces.KitchenService;
import com.dev.restaurantapp.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final KitchenService kitchenService;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public double getProfit() {
        double totalSum = 0;
        for (Order order : orderRepository.findAll()) {
            if (order.getIsPaid()) {
                double tempSum = 0;
                for (Long dishId : order.getDishes()) {
                    Optional<Dish> dishOpt = dishRepository.findById(dishId);
                    if (dishOpt.isPresent()) {
                        tempSum += dishOpt.get().getPrice();
                    }
                }
                if (order.getPriority().equals(OrderPriority.HIGH)) {
                    tempSum *= 1.5;
                }
                totalSum += tempSum;
            }
        }
        return totalSum;
    }

    @Override
    public String getOrderStatusById(Long id, Authentication authentication) {
        String username = authentication.getName();

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NoSuchItemInDatabaseException("No value present");
        }

        if (order.get().getUsername().equals(username)) {
            return order.get().getStatus().name();
        }

        throw new NotUserOrderException("Not your order");
    }

    @Override
    public Order makeOrder(AddOrderRequest request, Authentication authentication) {
        String username = getUsername(authentication);

        for (Map.Entry<Long, Integer> pair : request.getDishes().entrySet()) {
            Optional<Dish> dish = dishRepository.findById(pair.getKey());
            if (dish.isEmpty() || dish.get().getQuantity() < pair.getValue()) {
                throw new CannotMakeOrderException("No such position in menu or not enough dishes available");
            }
        }

        List<Long> dishIds = new ArrayList<>();
        request.getDishes().forEach((dishId, quantity) -> {
            for (int i = 0; i < quantity; i++) {
                dishIds.add(dishId);
            }
        });

        Order order = Order.builder()
                .username(username)
                .dishes(dishIds)
                .status(OrderStatus.IN_PROCESS)
                .priority(request.getPriority())
                .isPaid(false)
                .build();

        kitchenService.cook(order);

        return orderRepository.save(order);
    }

    @Override
    public Order addDish(Long dishId, Long orderId, Authentication authentication) {
        String username = getUsername(authentication);
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty() || dish.get().getQuantity() == 0) {
            throw new CannotMakeOrderException("No such position in menu or not enough dishes available");
        }

        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new NoSuchItemInDatabaseException("No value present");
        }

        if (!order.get().getUsername().equals(username)) {
            throw new NotUserOrderException("Not your order");
        }

        if (order.get().getStatus() == OrderStatus.IN_PROCESS) {
            List<Long> dishes = order.get().getDishes();
            dishes.add(dishId);
            try {
                return orderRepository.save(Order.builder()
                        .username(username)
                        .dishes(dishes)
                        .status(OrderStatus.IN_PROCESS)
                        .priority(order.get().getPriority())
                        .isPaid(false)
                        .build());
            } catch (IllegalArgumentException exception) {
                throw new RawCannotBeNullException(exception.getMessage());
            } catch (RuntimeException exception) {
                throw new NameAlreadyExistsException(exception.getMessage());
            }
        } else {
            throw new OrderAlreadyDoneException("Order was already done");
        }
    }

    @Override
    public void cancelOrder(Long id, Authentication authentication) {
        String username = getUsername(authentication);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NoSuchItemInDatabaseException("No value present");
        }

        if (!order.get().getUsername().equals(username)) {
            throw new NotUserOrderException("Not your order");
        }

        if (order.get().getStatus() == OrderStatus.IN_PROCESS) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderAlreadyDoneException("Order was already done");
        }
    }

    @Override
    public Order payForOrder(Long id, Authentication authentication) {
        String username = getUsername(authentication);

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NoSuchItemInDatabaseException("No value present");
        }

        if (!order.get().getUsername().equals(username)) {
            throw new NotUserOrderException("Not your order");
        }

        if (order.get().getStatus() == OrderStatus.DONE) {
            try {
                return orderRepository.save(Order.builder()
                        .username(username)
                        .dishes(order.get().getDishes())
                        .status(OrderStatus.DONE)
                        .priority(order.get().getPriority())
                        .isPaid(true)
                        .build());
            } catch (IllegalArgumentException exception) {
                throw new RawCannotBeNullException(exception.getMessage());
            } catch (RuntimeException exception) {
                throw new NameAlreadyExistsException(exception.getMessage());
            }
        } else {
            throw new OrderAlreadyDoneException("Order was not done");
        }
    }

    private String getUsername(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}
