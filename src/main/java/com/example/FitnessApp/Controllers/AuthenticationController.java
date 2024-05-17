package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.LoginResponseDTO;
import com.example.FitnessApp.Models.RegistrationDTO;
import com.example.FitnessApp.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public Object registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(),body.getRole()); //aici probabil voi modifica pt ca vor intra mai multi parametrii la inregistrare (email, last name, first name, etc(pana si roluri)
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(),body.getPassword());
    }
}

