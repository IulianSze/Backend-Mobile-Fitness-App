package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Models.Coach;
import com.example.FitnessApp.Repositories.CoachRepository;
import com.example.FitnessApp.Services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    CoachService coachService;
    @Autowired
    CoachRepository coachRepository;

    @GetMapping("/allcoaches")
    public List<Coach> getAllCoaches(){
        return coachService.getAllCoaches();}

    @PostMapping("/saveCoach")
    @Transactional
    public Coach saveCoach(@RequestBody Coach coach){
        return coachService.saveCoach(coach);
    }
}
