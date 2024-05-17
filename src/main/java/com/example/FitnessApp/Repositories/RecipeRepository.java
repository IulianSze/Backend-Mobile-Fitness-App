package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
   List<Recipe> findByIngredientsIn(List<String> ingredientsNames);

   @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE LOWER(i.ingredientName) IN :ingredientNames GROUP BY r HAVING COUNT(DISTINCT i) = :ingredientCount")
   List<Recipe> findByAllIngredientNamesIgnoreCase(@Param("ingredientNames") List<String> ingredientNames, @Param("ingredientCount") Long ingredientCount);
}