package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.AlimentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AlimentRepository extends JpaRepository<AlimentModel,Integer> {
    AlimentModel findByAlimentName(String alimentName);
}
