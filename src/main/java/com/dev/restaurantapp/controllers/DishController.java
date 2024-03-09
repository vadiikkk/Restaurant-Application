package com.dev.restaurantapp.controllers;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.services.interfaces.DishService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    @GetMapping("/actual-menu")
    @PreAuthorize("isAuthenticated()")
    public List<Dish> getActualMenu() {
        return dishService.getActualMenu();
    }

    @GetMapping("/full-menu")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Dish> getFullMenu() {
        return dishService.getFullMenu();
    }
}
