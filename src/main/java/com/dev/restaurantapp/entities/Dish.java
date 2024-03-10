package com.dev.restaurantapp.entities;

import com.dev.restaurantapp.enumerable.DishStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "time_to_cook", nullable = false)
    private int timeToCook;

    @Column(name = "status", nullable = false)
    private DishStatus dishStatus;

}
