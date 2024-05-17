package com.example.FitnessApp.Services;

import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Repositories.AlimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentService {
    @Autowired
    AlimentRepository alimentRepository;
    public AlimentModel findByName (String alimentName){
        return alimentRepository.findByAlimentName(alimentName);
    }
}
