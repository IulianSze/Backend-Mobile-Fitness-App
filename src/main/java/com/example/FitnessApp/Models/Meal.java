package com.example.FitnessApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;

    @Column(name = "MealDate")
    private LocalDate mealDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "MealType")
    private MealType mealType;

    @Column(name = "user_id")
    private Integer userId;

//    @OneToMany(mappedBy = "meal")
//    private List<MealFoodItem> mealFoodItems = new ArrayList<>();
//
//    @OneToOne(mappedBy = "meal", cascade = CascadeType.ALL)
//    private MealSummary mealSummary;

    // Getters and setters
}