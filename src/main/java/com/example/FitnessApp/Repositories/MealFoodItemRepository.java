package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Models.MealFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealFoodItemRepository extends JpaRepository<MealFoodItem, Integer>{
    List<MealFoodItem> findAllByMealId(Integer mealId);

    Optional<MealFoodItem> findByMealIdAndAlimentName(int mealId, String alimentName);
}
