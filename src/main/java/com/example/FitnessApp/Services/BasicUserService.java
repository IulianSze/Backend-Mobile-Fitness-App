package com.example.FitnessApp.Services;

import com.example.FitnessApp.DTOs.BasicUserDTO;
import com.example.FitnessApp.DTOs.BasicUserMACROS;
import com.example.FitnessApp.Models.ApplicationUser;
import com.example.FitnessApp.Models.BasicUser;
import com.example.FitnessApp.Repositories.BasicUserRepository;
import com.example.FitnessApp.Repositories.UserRepository;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BasicUserService {
    @Autowired
    BasicUserRepository basicUserRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserRepository userRepository;

    public BasicUser getByUserId(int userId){
        ApplicationUser applicationUser = userRepository.findByUserId(userId);
        return basicUserRepository.findBasicUserByApplicationUser(applicationUser);
    }

    public BasicUserMACROS getUserGeneralMacrosByUserId(int userId){
        ApplicationUser applicationUser = userRepository.findByUserId(userId);
        BasicUser basicUser = basicUserRepository.findBasicUserByApplicationUser(applicationUser);
        BasicUserMACROS basicUserMACROS = new BasicUserMACROS();
        basicUserMACROS.setKcalDaily(basicUser.getKcalDaily());
        basicUserMACROS.setCarbohidratesDaily(basicUser.getCarbohidratesDaily());
        basicUserMACROS.setProteinsDaily(basicUser.getProteinsDaily());
        basicUserMACROS.setSodiumDaily(basicUser.getSodiumDaily());
        basicUserMACROS.setMagneziumDaily(basicUser.getMagneziumDaily());
        basicUserMACROS.setFibersDaily(basicUser.getFibersDaily());
        basicUserMACROS.setVitADaily(basicUser.getVitADaily());
        basicUserMACROS.setVitBDaily(basicUser.getVitBDaily());
        basicUserMACROS.setVitCDaily(basicUser.getVitCDaily());
        return basicUserMACROS;
    }

    public BasicUserDTO getUserGeneralParameters(int userId){
        ApplicationUser applicationUser = userRepository.findByUserId(userId);
        BasicUser basicUser = basicUserRepository.findBasicUserByApplicationUser(applicationUser);
        BasicUserDTO basicUserDTO = new BasicUserDTO();
        basicUserDTO.setFullname(basicUser.getFullname());
        basicUserDTO.setGender(basicUser.getGender());
        basicUserDTO.setAge(basicUser.getAge());
        basicUserDTO.setHeight(basicUser.getHeight());
        basicUserDTO.setWeight(basicUser.getWeight());
        basicUserDTO.setLifestyle(basicUser.getLifestyle());
        basicUserDTO.setGoal(basicUser.getGoal());
        basicUserDTO.setEmail(basicUser.getEmail());
        return basicUserDTO;
    }
    private static final Map<String, Double> lifestyleMap = new HashMap<>();
    static {
        lifestyleMap.put("Sedentary", 1.2);
        lifestyleMap.put("Medium", 1.375);
        lifestyleMap.put("Active", 1.55);
        lifestyleMap.put("Very Active", 1.725);
    }
    public BasicUser registerUser(BasicUser basicUser){
        int kcalDaily; double carboDaily=0, proteinsDaily=0, fibersDaily=0, vitADaily=0, vitBDaily=0, vitCDaily=0, magneziumDaily=0, sodiumDaily=0;
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser=authenticationService.registerUser(basicUser.getApplicationUser().getUsername(), basicUser.getApplicationUser().getPassword(), "USER");

        double weight= basicUser.getWeight();
        double age= basicUser.getAge();
        String gender = basicUser.getGender();
        String lifestyle= basicUser.getLifestyle();
        String goal = basicUser.getGoal();
        int height = basicUser.getHeight();
        String email= basicUser.getEmail();
        Double lifestyleParam = lifestyleMap.getOrDefault(lifestyle,0.0);

        if(gender.equals("Male")){
          double  kcalDailyDouble = lifestyleParam * (66 + (13.7 * weight) + (5 * height) - (6.8 * age));
          kcalDaily = (int) Math.round(kcalDailyDouble);
            vitADaily=900.0;
            vitCDaily=110.0;
            sodiumDaily=2300.0;


        }else{
            double  kcalDailyDouble = lifestyleParam * (665 + (9.5 * weight) + (1.8 * height) - (4.7 * age));
            kcalDaily = (int) Math.round(kcalDailyDouble);
            vitADaily=700.0;
            vitCDaily=80.0;
            sodiumDaily=1500.0;
        }
        System.out.println(kcalDaily);
        if(goal.equals("Lose weight")){
            kcalDaily=kcalDaily-450;

        }else if(goal.equals("Gain weight")) {
            kcalDaily=kcalDaily+450;

        }

        proteinsDaily = 35.0 / 100.0 * kcalDaily / 4;
        carboDaily = 30.0 / 100.0 * kcalDaily / 4;
        fibersDaily = 14.0 * (double)(kcalDaily / 1000) * 0.5;
        vitBDaily = 0.0024;
        magneziumDaily = 300;

        BasicUser newUser = new BasicUser();

        newUser.setUser_Id(applicationUser.getUserId());

        newUser.setFullname(basicUser.getFullname());
        newUser.setGender(gender);
        newUser.setHeight(height);
        newUser.setWeight(weight);
        newUser.setLifestyle(basicUser.getLifestyle());
        newUser.setGoal(goal);
        newUser.setEmail(basicUser.getEmail());
        newUser.setAge(basicUser.getAge());

        newUser.setKcalDaily(kcalDaily);
        newUser.setProteinsDaily(proteinsDaily);
        newUser.setCarbohidratesDaily(carboDaily);
        newUser.setFibersDaily(fibersDaily);
        newUser.setVitADaily(vitADaily);
        newUser.setVitBDaily(vitBDaily);
        newUser.setVitCDaily(vitCDaily);
        newUser.setMagneziumDaily(magneziumDaily);
        newUser.setSodiumDaily(sodiumDaily);

        newUser.setApplicationUser(applicationUser);

        return basicUserRepository.save(newUser);
    }
}
