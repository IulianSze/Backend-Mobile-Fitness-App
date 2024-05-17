package com.example.FitnessApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealSummaryOnlyMacros {
    private int kcal;
    private double carbohidrates;
    private double proteins;
    private double fibers;
    private double vitA;
    private double vitB;
    private double vitC;
    private double magnezium;
    private double sodium;
}
