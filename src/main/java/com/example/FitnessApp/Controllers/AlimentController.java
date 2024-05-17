package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Repositories.AlimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/user/aliments") -daca vreau sa ceara token
@RequestMapping("/auth/aliments") //-las asa momentan(sa nu uit sa modific), ca sa verific mai usor in aplicatie fara sa ma mai loghez
@CrossOrigin("*")
public class AlimentController {
    @Autowired
    private AlimentRepository alimentRepository;

    @GetMapping("/")
    public List<AlimentModel> getAllAliments(){
        return alimentRepository.findAll();}

}
