package com.example.FitnessApp.Services;

import com.example.FitnessApp.DTOs.MealAndSummaryDTO;
import com.example.FitnessApp.DTOs.MealDTO;
import com.example.FitnessApp.DTOs.MealInfoDTO;
import com.example.FitnessApp.DTOs.MealInfoRelevantForLogPageDTO;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Models.MealSummary;
import com.example.FitnessApp.Models.MealType;
import com.example.FitnessApp.Repositories.MealFoodItemRepository;
import com.example.FitnessApp.Repositories.MealRepository;
import com.example.FitnessApp.Repositories.MealSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealSummaryRepository mealSummaryRepository;
    @Autowired
    private MealFoodItemRepository mealFoodItemRepository;

    public MealSummary computeAndGetAllDayInfoBasedOnMeals(int userId, LocalDate date){
        //LocalDate date  = LocalDate.now();
        int kcal=0; double carbo=0,proteins=0,fibers=0,magnezium=0,sodium=0,vitA=0,vitB=0,vitC=0;
        List<Meal> meals = getMealsForUserAndDate(userId,date);
        for(Meal meal : meals) {
            Optional<MealSummary> existingMealSummary = mealSummaryRepository.findByMeal_MealId(meal.getMealId());
            MealSummary mealSummary = existingMealSummary.orElse(null);
            kcal = kcal + mealSummary.getKcal();
            carbo = carbo + mealSummary.getCarbohidrates();
            proteins = proteins + mealSummary.getProteins();
            fibers = fibers + mealSummary.getFibers();
            magnezium = magnezium + mealSummary.getMagnezium();
            sodium = sodium + mealSummary.getSodium();
            vitA = vitA + mealSummary.getVitA();
            vitB = vitB + mealSummary.getVitB();
            vitC = vitC + mealSummary.getVitC();
        }
        MealSummary mealSummary = new MealSummary();
              mealSummary.setKcal(kcal);
              mealSummary.setCarbohidrates(carbo);
              mealSummary.setProteins(proteins);
              mealSummary.setFibers(fibers);
              mealSummary.setVitA(vitA);
              mealSummary.setVitB(vitB);
              mealSummary.setVitC(vitC);
              mealSummary.setMagnezium(magnezium);
              mealSummary.setSodium(sodium);

     return mealSummary;

    }
    public Meal getMealForUserMealTypeAndDate(int userId, MealType mealType, LocalDate date){
        Meal meal = mealRepository.findByUserIdAndMealDateAndMealType(userId,date,mealType);
        return meal;
    }
    public Meal saveMeal(MealDTO mealDto) {
        Meal meal = new Meal();
        meal.setMealDate(mealDto.getMealDate());
        meal.setMealType(mealDto.getMealType());
        meal.setUserId(mealDto.getUserId());
        return mealRepository.save(meal);
    }
    public List<Meal> getMealsForUserAndDate(Integer userId, LocalDate date) {
    List<Meal> meals = mealRepository.findByUserIdAndMealDate(userId, date);
    return meals;
    }
    public List<MealInfoRelevantForLogPageDTO> getMealInfoForLogPage(Integer userId, LocalDate date){
        List<Meal> meals = mealRepository.findByUserIdAndMealDate(userId,date);

        List<MealInfoRelevantForLogPageDTO> mealInfoList = new ArrayList<>();
        for (Meal meal: meals){
           Optional<MealSummary> existingMealSummary = mealSummaryRepository.findByMeal_MealId(meal.getMealId());
            MealSummary mealSummary = existingMealSummary.orElse(null);
            List<MealFoodItem> mealFoodItems = mealFoodItemRepository.findAllByMealId(meal.getMealId());

            MealInfoRelevantForLogPageDTO mealInfo = new MealInfoRelevantForLogPageDTO();
            mealInfo.setMeal(meal);
            mealInfo.setMealFoodItems(mealFoodItems);
            mealInfo.setKcal(mealSummary != null ? mealSummary.getKcal() : 0);

            mealInfoList.add(mealInfo);
        }
        return mealInfoList;
    }
    public List<MealInfoDTO> getMealInfoForUserAndDate(Integer userId, LocalDate date) {
        List<Meal> meals = mealRepository.findByUserIdAndMealDate(userId, date);

        List<MealInfoDTO> mealInfoList = new ArrayList<>();

        for (Meal meal : meals) {
            // Retrieve meal summary for the meal
            Optional<MealSummary> existingMealSummary = mealSummaryRepository.findByMeal_MealId(meal.getMealId());
            MealSummary mealSummary= existingMealSummary.get();
            // Retrieve meal food items for the meal
            List<MealFoodItem> mealFoodItems = mealFoodItemRepository.findAllByMealId(meal.getMealId());

            // Create MealInfoDTO object with meal information
            MealInfoDTO mealInfo = new MealInfoDTO();
            mealInfo.setMeal(meal);
            mealInfo.setMealSummary(mealSummary);
            mealInfo.setMealFoodItems(mealFoodItems);

            mealInfoList.add(mealInfo);
        }

        return mealInfoList;
    }

}
