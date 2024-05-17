package com.example.FitnessApp.Services;

import com.example.FitnessApp.DTOs.RecipeDTO;
import com.example.FitnessApp.Models.Ingredient;
import com.example.FitnessApp.Models.Recipe;
import com.example.FitnessApp.Repositories.IngredientRepository;
import com.example.FitnessApp.Repositories.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    public List<Recipe> findAllRecipes(){
        return recipeRepository.findAll();
    }
    public List<Recipe> getRecipesContainingAllIngredients(List<String> ingredientNames) {
        Long ingredientCount = (long) ingredientNames.size();
        return recipeRepository.findByAllIngredientNamesIgnoreCase(ingredientNames, ingredientCount);
    }
    @Transactional
    public Recipe saveRecipe(RecipeDTO recipe) {
        Recipe savedRecipe = new Recipe();
        savedRecipe.setRecipeName(recipe.getRecipeName());
        savedRecipe.setPhotoUrl(recipe.getPhotoUrl());
        savedRecipe.setKcal(recipe.getKcal());
        savedRecipe.setCarbohidrates(recipe.getCarbohidrates());
        savedRecipe.setProteins(recipe.getProteins());
        savedRecipe.setFibers(recipe.getFibers());
        savedRecipe.setDescription(recipe.getDescription());
        savedRecipe.setCookingInstructions(recipe.getCookingInstructions());

        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredientIn : recipe.getIngredients()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientName(ingredientIn.getIngredientName());
            ingredient.setQuantity(ingredientIn.getQuantity());
            ingredient.setRecipe(savedRecipe);
            ingredients.add(ingredient);
        }
        savedRecipe.setIngredients(ingredients);


        return recipeRepository.save(savedRecipe);
    }
}