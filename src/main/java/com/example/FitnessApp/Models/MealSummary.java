package com.example.FitnessApp.Models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mealSummaryId")
    private Integer mealSummaryId;

    @Column(name = "Kcal")
    private int kcal;

    @Column(name = "Carbohidrates")
    private double carbohidrates;

    @Column(name = "Proteins")
    private double proteins;

    @Column(name = "Fibers")
    private double fibers;

    @Column(name = "VitA")
    private double vitA;

    @Column(name = "VitB")
    private double vitB;

    @Column(name = "VitC")
    private double vitC;

    @Column(name = "Magnezium")
    private double magnezium;

    @Column(name = "Sodium")
    private double sodium;

    @OneToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    // Getters and setters
}