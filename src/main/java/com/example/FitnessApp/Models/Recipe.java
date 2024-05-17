package com.example.FitnessApp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private int recipeId;

    @Column(name="recipeName")
    private String recipeName;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;


    private String description;
    private int kcal;
    private double carbohidrates;
    private double proteins;
    private double fibers;
    private String cookingInstructions;
}
