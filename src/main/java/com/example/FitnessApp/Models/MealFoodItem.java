package com.example.FitnessApp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MealFoodItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MealFoodItemId")
    private Integer mealFoodItemId;

    @Column(name = "meal_id")
    private Integer mealId;

    @Column(name = "aliment_name")
    private String alimentName;

    @Column(name="Quantity")
    private Integer quantity;
    // Getters and setters
}