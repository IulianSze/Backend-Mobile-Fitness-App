package com.example.FitnessApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BasicUserDTO {
    private int userId;
    private String fullname;
    private String gender;
    private int age;
    private int height;
    private double weight;
    private String lifestyle;
    private String goal;
    private String email;
}
