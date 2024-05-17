package com.example.FitnessApp.Repositories;

import com.example.FitnessApp.Models.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Integer> {
    List<Videos> findAllByMuscleGroup(String muscleGroup);

    List<Videos> findAllByPerformanceLevel(String performance);

    List<Videos> findAllByMuscleGroupAndPerformanceLevel(String muscleGroup, String performance);
}
