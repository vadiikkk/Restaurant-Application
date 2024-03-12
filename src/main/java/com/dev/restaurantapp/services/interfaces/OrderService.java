package com.dev.restaurantapp.services.interfaces;

import com.dev.restaurantapp.dto.AddOrderRequest;
import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.entities.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    double getProfit();

    String getOrderStatusById(Long id, Authentication authentication);

    Order makeOrder(AddOrderRequest request, Authentication authentication);

    Order addDish(Long dishId, Long orderId, Authentication authentication);

    void cancelOrder(Long id, Authentication authentication);

    Order payForOrder(Long id, Authentication authentication);
}
