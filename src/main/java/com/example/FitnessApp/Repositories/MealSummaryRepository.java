package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.DTOs.MealDTO;
import com.example.FitnessApp.DTOs.MealSummaryOnlyMacros;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealSummaryRepository extends JpaRepository<MealSummary, Integer> {
    Optional<MealSummary> findByMeal_MealId(Integer mealId);

}
