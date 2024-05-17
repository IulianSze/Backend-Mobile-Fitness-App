package com.example.FitnessApp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Coach {
    @Id
    private int coachId;

    private String coachName;
    private String photoUrl;
    private String specialization;
    private String description;
    private int age;
    private int experience;
    private String performances;

    @OneToOne
    @JoinColumn(name = "userId")
    private ApplicationUser applicationUser;
}
