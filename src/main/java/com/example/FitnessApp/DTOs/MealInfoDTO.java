package com.example.FitnessApp.DTOs;

import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Models.MealSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealInfoDTO {
    private Meal meal;
    private MealSummary mealSummary;
    private List<MealFoodItem> mealFoodItems;
}
