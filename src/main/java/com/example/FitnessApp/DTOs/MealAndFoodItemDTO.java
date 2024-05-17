package com.example.FitnessApp.DTOs;

import com.example.FitnessApp.Models.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealAndFoodItemDTO {
    //Meal:
    private LocalDate mealDate;
    private MealType mealType;
    private Integer userId;
    //MealFoodItem:
    private Integer mealId;
    private String alimentName;
    private Integer quantity;
}
