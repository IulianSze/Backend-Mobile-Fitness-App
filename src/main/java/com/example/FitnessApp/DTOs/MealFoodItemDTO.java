package com.example.FitnessApp.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealFoodItemDTO {
    private Integer mealId;
    private String alimentName;
    private Integer quantity;
}
