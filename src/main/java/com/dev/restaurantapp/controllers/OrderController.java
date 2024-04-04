package com.dev.restaurantapp.controllers;

import com.dev.restaurantapp.dto.AddOrderRequest;
import com.dev.restaurantapp.entities.Order;
import com.dev.restaurantapp.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/profit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public double getProfit() {
        return orderService.getProfit();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getOrderStatus(@PathVariable Long id) {
        return orderService.getOrderStatusById(id, SecurityContextHolder.getContext().getAuthentication());
    }

    @PostMapping("/make")
    @PreAuthorize("isAuthenticated()")
    public Order makeOrder(@RequestBody AddOrderRequest dish) {
        return orderService.makeOrder(dish, SecurityContextHolder.getContext().getAuthentication());
    }

    @PutMapping("/addDish/{id}")
    @PreAuthorize("isAuthenticated()")
    public Order addDish(@PathVariable("id") Long dishId, @RequestBody Long orderId) {
        return orderService.addDish(dishId, orderId, SecurityContextHolder.getContext().getAuthentication());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void cancelOrder(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(orderId, SecurityContextHolder.getContext().getAuthentication());
    }

    @PutMapping("/pay/{id}")
    @PreAuthorize("isAuthenticated()")
    public Order payForOrder(@PathVariable("id") Long id) {
        return orderService.payForOrder(id, SecurityContextHolder.getContext().getAuthentication());
    }
}
