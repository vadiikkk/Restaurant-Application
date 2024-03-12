package com.dev.restaurantapp.repositories;

import com.dev.restaurantapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
