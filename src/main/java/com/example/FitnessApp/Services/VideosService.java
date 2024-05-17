package com.example.FitnessApp.Services;

import com.example.FitnessApp.Models.Videos;
import com.example.FitnessApp.Repositories.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosService {

    @Autowired
    VideosRepository videosRepository;

    public List<Videos> filterVideos(String muscle, String performance){
        if(muscle == null){
            return videosRepository.findAllByPerformanceLevel(performance);
        }
        else if(performance == null){
            return videosRepository.findAllByMuscleGroup(muscle);
        }
        else
            return videosRepository.findAllByMuscleGroupAndPerformanceLevel(muscle,performance);

    }
}
