package com.dev.restaurantapp.controllers;

import com.dev.restaurantapp.entities.User;
import com.dev.restaurantapp.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/reg")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User Register(@RequestBody User user) {
        return userService.Register(user);
    }
}
