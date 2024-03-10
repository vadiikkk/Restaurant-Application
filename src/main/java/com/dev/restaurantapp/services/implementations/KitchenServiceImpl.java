package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.entities.Order;
import com.dev.restaurantapp.services.interfaces.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KitchenServiceImpl implements KitchenService {
    @Override
    public void cook(Order order) {

    }
}
