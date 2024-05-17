package com.example.FitnessApp.DTOs;

import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Models.MealSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealInfoRelevantForLogPageDTO {
    private Meal meal;
    private List<MealFoodItem> mealFoodItems;
    int kcal;
}
