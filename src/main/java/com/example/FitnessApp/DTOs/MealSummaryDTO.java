package com.example.FitnessApp.DTOs;

import com.example.FitnessApp.Models.Meal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class MealSummaryDTO {
    private double kcal;
    private double carbohidrates;
    private double proteins;
    private double fibers;
    private double vitA;
    private double vitB;
    private double vitC;
    private double magnezium;
    private double sodium;
    private Meal meal;
}
