package com.dev.restaurantapp.controllers;

import com.dev.restaurantapp.entities.Dish;
import com.dev.restaurantapp.services.interfaces.DishService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Dish addDish(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Dish updateDish(@RequestBody Dish dish) {
        return dishService.updateDish(dish);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteDish(@PathVariable("id") Long id) {
        dishService.deleteDish(id);
    }
}
