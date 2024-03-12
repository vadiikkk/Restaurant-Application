package com.dev.restaurantapp.dto;

import com.dev.restaurantapp.enumerable.OrderPriority;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class AddOrderRequest {

    private Map<Long, Integer> dishes;

    private OrderPriority priority;

}
