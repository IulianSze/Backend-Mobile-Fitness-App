package com.example.FitnessApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasicUserMACROS {
    public int kcalDaily;
    private double carbohidratesDaily;
    private double proteinsDaily;
    private double fibersDaily;
    private double vitADaily;
    private double vitBDaily;
    private double vitCDaily;
    private double magneziumDaily;
    private double sodiumDaily;
}
