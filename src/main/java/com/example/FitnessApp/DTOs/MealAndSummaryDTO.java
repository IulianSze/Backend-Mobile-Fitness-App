package com.example.FitnessApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealAndSummaryDTO {
    private MealDTO mealDto;
    private MealSummaryDTO mealSummaryDto;
    private List<MealFoodItemDTO> mealFoodItemsDTO;
}
