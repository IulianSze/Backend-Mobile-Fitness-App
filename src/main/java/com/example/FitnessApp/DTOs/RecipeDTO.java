package com.example.FitnessApp.DTOs;

import com.example.FitnessApp.Models.Ingredient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private int recipeId;
    private String recipeName;
    private String photoUrl;
    private List<Ingredient> ingredients;
    private String description;
    private int kcal;
    private double carbohidrates;
    private double proteins;
    private double fibers;
    private String cookingInstructions;
}

