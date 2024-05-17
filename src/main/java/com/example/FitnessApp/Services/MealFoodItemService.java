package com.example.FitnessApp.Services;

import com.example.FitnessApp.DTOs.MealDTO;
import com.example.FitnessApp.DTOs.MealFoodItemDTO;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Repositories.MealFoodItemRepository;
import com.example.FitnessApp.Repositories.MealSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealFoodItemService {
    @Autowired
    private MealFoodItemRepository mealFoodItemRepository;

    public MealFoodItem saveMealFoodItem(MealFoodItemDTO mealFoodItemDTO) {
        MealFoodItem mealFoodItem = new MealFoodItem();
        mealFoodItem.setMealId(mealFoodItemDTO.getMealId());
        mealFoodItem.setAlimentName(mealFoodItemDTO.getAlimentName());
        mealFoodItem.setQuantity(mealFoodItemDTO.getQuantity());
        return mealFoodItemRepository.save(mealFoodItem);
    }

    public MealFoodItem updateMealFoodItem(MealFoodItemDTO mealFoodItemDTO){
        int mealId = mealFoodItemDTO.getMealId();
        String alimentName= mealFoodItemDTO.getAlimentName();
        int newQuantity= mealFoodItemDTO.getQuantity();
        Optional<MealFoodItem> existingItemOptional = mealFoodItemRepository.findByMealIdAndAlimentName(mealId, alimentName);

        if (existingItemOptional.isPresent()) {
            MealFoodItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(newQuantity);
            return mealFoodItemRepository.save(existingItem);
        } else {
            // Item not found, you can handle this case according to your requirements
            return null;
        }
    }

    public List<MealFoodItem> getAlimentsFromMeal(int mealId){
        return mealFoodItemRepository.findAllByMealId(mealId);
    }
}
