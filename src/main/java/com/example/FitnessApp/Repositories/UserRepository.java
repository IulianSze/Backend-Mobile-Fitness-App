package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);
    ApplicationUser findByUserId(int userId);

}
