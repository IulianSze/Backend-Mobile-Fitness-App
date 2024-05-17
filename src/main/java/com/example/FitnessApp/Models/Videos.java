package com.example.FitnessApp.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videos_id")
    private Integer videosID;

    @Column(name = "video_name")
    private String videoName;

    @Column(name = "description")
    private String description;

    @Column(name="muscle_group")
    private String muscleGroup;

    @Column(name="performance_level")
    private String performanceLevel;

}
