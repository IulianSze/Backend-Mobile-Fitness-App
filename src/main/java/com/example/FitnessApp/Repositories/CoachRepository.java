package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Coach;
import com.example.FitnessApp.Models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Integer>{
    Coach findByApplicationUser(ApplicationUser applicationUser);
}
