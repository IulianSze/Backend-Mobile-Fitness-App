package com.example.FitnessApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BasicUser {
    @Id
    private int user_Id;

    public String fullname;

    public String gender;
    public int age;
    public int height;
    public double weight;
    public int kcalDaily;
    private double carbohidratesDaily;
    private double proteinsDaily;
    private double fibersDaily;
    private double vitADaily;
    private double vitBDaily;
    private double vitCDaily;
    private double magneziumDaily;
    private double sodiumDaily;
    public String lifestyle;
    public String goal;
    public String email;

    @OneToOne
    @JoinColumn(name = "userId")
    private ApplicationUser applicationUser;
}
