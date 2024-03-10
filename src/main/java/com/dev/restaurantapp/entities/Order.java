package com.dev.restaurantapp.entities;

import com.dev.restaurantapp.enumerable.OrderPriority;
import com.dev.restaurantapp.enumerable.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "_order")
@Builder
@AllArgsConstructor
public class Order {

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @ElementCollection
    @Column(name = "dishes_id")
    private List<Long> dishes;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "priority")
    private OrderPriority priority;

    @Column(name = "isPaid")
    private Boolean isPaid;
}
