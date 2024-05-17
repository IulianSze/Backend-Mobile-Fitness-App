package com.example.FitnessApp.Services;
import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.Coach;
import com.example.FitnessApp.Models.Role;
import com.example.FitnessApp.Repositories.CoachRepository;
import com.example.FitnessApp.Repositories.RoleRepository;
import com.example.FitnessApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CoachService {
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepository userRepository;

    public List<Coach> getAllCoaches(){
        return coachRepository.findAll();
    }
    public Coach saveCoach(Coach coach) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser=authenticationService.registerUser(coach.getApplicationUser().getUsername(), coach.getApplicationUser().getPassword(),"COACH");

        Coach newCoach = new Coach();
        newCoach.setCoachName(coach.getCoachName());
        newCoach.setCoachId(applicationUser.getUserId());
        newCoach.setPhotoUrl(coach.getPhotoUrl());
        newCoach.setSpecialization(coach.getSpecialization());
        newCoach.setDescription(coach.getDescription());
        newCoach.setAge(coach.getAge());
        newCoach.setExperience(coach.getExperience());
        newCoach.setPerformances(coach.getPerformances());
        newCoach.setApplicationUser(applicationUser);
        return coachRepository.save(newCoach);
    }
}
