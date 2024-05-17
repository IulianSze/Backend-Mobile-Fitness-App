package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicUserRepository extends JpaRepository<BasicUser, Integer> {
    public BasicUser findBasicUserByApplicationUser(ApplicationUser applicationUser);
}
