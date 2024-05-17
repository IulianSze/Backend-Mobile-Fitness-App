package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
}
