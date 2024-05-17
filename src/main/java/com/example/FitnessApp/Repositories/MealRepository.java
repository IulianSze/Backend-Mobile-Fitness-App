package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.DTOs.MealDTO;
import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findByUserIdAndMealDate(Integer userId, LocalDate date);
    Meal findByUserIdAndMealDateAndMealType(Integer userId, LocalDate mealDate, MealType mealType);
}
