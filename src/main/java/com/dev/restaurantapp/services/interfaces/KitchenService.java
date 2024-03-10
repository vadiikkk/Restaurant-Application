package com.dev.restaurantapp.services.interfaces;

import com.dev.restaurantapp.entities.Order;

public interface KitchenService {
    void cook(Order order) throws InterruptedException;
}
