package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.DTOs.RecipeDTO;
import com.example.FitnessApp.Models.Recipe;
import com.example.FitnessApp.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/recipes")
@CrossOrigin("*")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public List<Recipe> findAllRecipes(){
        return recipeService.findAllRecipes();
    }
    @GetMapping("/byIngredients")
    public List<Recipe> getRecipesByIngredients(@RequestParam List<String> ingredients) {
        return recipeService.getRecipesContainingAllIngredients(ingredients);
    }
    @PostMapping("/save")
    public Recipe saveRecipe(@RequestBody RecipeDTO recipe) {
        return recipeService.saveRecipe(recipe);
    }
}