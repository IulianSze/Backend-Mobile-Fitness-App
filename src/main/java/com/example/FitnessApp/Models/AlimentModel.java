package com.example.FitnessApp.Models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlimentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AlimentId")
    private Integer alimentId;

    @Column(name="AlimentName")
    private String alimentName;

    @Column(name = "Kcal")
    private Integer kcal;

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


}
