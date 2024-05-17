package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.DTOs.BasicUserDTO;
import com.example.FitnessApp.DTOs.BasicUserMACROS;
import com.example.FitnessApp.Models.BasicUser;
import com.example.FitnessApp.Models.Coach;
import com.example.FitnessApp.Repositories.BasicUserRepository;
import com.example.FitnessApp.Services.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/basicuser")
@CrossOrigin("*")
public class BasicUserController {
    @Autowired
    BasicUserService basicUserService;

    @GetMapping("/getUser")
    public BasicUser getByUserId(@RequestParam int userId){
        return basicUserService.getByUserId(userId);
    }

    @GetMapping("/getMacros")
    public BasicUserMACROS getMacrosById(@RequestParam int userId){
        return basicUserService.getUserGeneralMacrosByUserId(userId);
    }
    @GetMapping("/getInfo")
    public BasicUserDTO getGeneralInfo(@RequestParam int userId){
        return basicUserService.getUserGeneralParameters(userId);
    }

    @PostMapping("/saveUser")
    @Transactional
    public BasicUser registerUser(@RequestBody BasicUser basicUser){
        return basicUserService.registerUser(basicUser);
    }
}
