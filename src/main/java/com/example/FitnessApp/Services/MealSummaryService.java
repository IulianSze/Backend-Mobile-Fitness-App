package com.example.FitnessApp.Services;

import com.example.FitnessApp.DTOs.MealDTO;
import com.example.FitnessApp.DTOs.MealSummaryDTO;
import com.example.FitnessApp.DTOs.MealSummaryOnlyMacros;
import com.example.FitnessApp.Models.AlimentModel;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Models.MealSummary;
import com.example.FitnessApp.Repositories.AlimentRepository;
import com.example.FitnessApp.Repositories.MealFoodItemRepository;
import com.example.FitnessApp.Repositories.MealRepository;
import com.example.FitnessApp.Repositories.MealSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealSummaryService {

    @Autowired
    private MealSummaryRepository mealSummaryRepository;
    @Autowired
    private AlimentRepository alimentRepository;
    @Autowired
    private MealFoodItemRepository mealFoodItemRepository;

    // here i get all the foods consumed at a meal (by meal id) and calculate all the macros and save them in saveMealSummary..
    // i think a better approach tho is to update the meal summary table each time an aliment is added, that way i can also
    // manage the sums in the add food page
    // - NU E BINE AM PIERDUT TIMP CA SE ADUNA PREA MULT.. TRB PT FIECARE ALIMENT -.-
//    public MealSummary saveMealSummary(Meal meal) {
//        Optional<MealSummary> existingMealSummaryOptional = mealSummaryRepository.findByMeal_MealId(meal.getMealId());
//      if (existingMealSummaryOptional.isPresent()) {
//          MealSummary existingMealSummary = existingMealSummaryOptional.get();
//          Integer kcalSum = existingMealSummary.getKcal();
//          double carboSum = existingMealSummary.getCarbohidrates(), proteinsSum = existingMealSummary.getProteins()
//                  , fibersSum = existingMealSummary.getFibers(), vitASum = existingMealSummary.getVitA(),
//                  vitBSum = existingMealSummary.getVitB(), vitCSum = existingMealSummary.getVitC(),
//                  magneziumSum = existingMealSummary.getMagnezium(), sodiumSum = existingMealSummary.getSodium();
//            computeCalculations(meal.getMealId(),kcalSum, carboSum, proteinsSum, fibersSum, vitASum, vitBSum, vitCSum, magneziumSum, sodiumSum);
//            existingMealSummary.setKcal(kcalSum);
//            existingMealSummary.setCarbohidrates(carboSum);
//            existingMealSummary.setProteins(proteinsSum);
//            existingMealSummary.setFibers(fibersSum);
//            existingMealSummary.setVitA(vitASum);
//            existingMealSummary.setVitB(vitBSum);
//            existingMealSummary.setVitC(vitCSum);
//            existingMealSummary.setMagnezium(magneziumSum);
//            existingMealSummary.setSodium(sodiumSum);
//
//            existingMealSummary.setMeal(meal);
//          return mealSummaryRepository.save(existingMealSummary);
//      }else {
//          Integer kcalSum = 0;
//          double carboSum = 0, proteinsSum = 0, fibersSum = 0, vitASum = 0, vitBSum = 0, vitCSum = 0, magneziumSum = 0, sodiumSum = 0;
//          MealSummary mealSummary = new MealSummary();
//
//          mealSummary.setKcal(kcalSum);
//          mealSummary.setCarbohidrates(carboSum);
//          mealSummary.setProteins(proteinsSum);
//          mealSummary.setFibers(fibersSum);
//          mealSummary.setVitA(vitASum);
//          mealSummary.setVitB(vitBSum);
//          mealSummary.setVitC(vitCSum);
//          mealSummary.setMagnezium(magneziumSum);
//          mealSummary.setSodium(sodiumSum);
//
//          mealSummary.setMeal(meal);
//          return mealSummaryRepository.save(mealSummary);
//      }
//    }
    public Optional<MealSummary> getMealSummary(int mealId){
       return mealSummaryRepository.findByMeal_MealId(mealId);
    }
    public void computeCalculations(int mealId, int kcalSum, double carboSum, double proteinsSum, double fibersSum , double vitASum, double vitBSum, double vitCSum, double magneziumSum, double sodiumSum){
        List<MealFoodItem> mealFoodItems = mealFoodItemRepository.findAllByMealId(mealId);

        for (MealFoodItem mealFoodItem : mealFoodItems) {
            AlimentModel alimentModel = alimentRepository.findByAlimentName(mealFoodItem.getAlimentName());
            int quantityPercent = mealFoodItem.getQuantity() / 100;
            kcalSum += alimentModel.getKcal() * quantityPercent;
            carboSum += alimentModel.getCarbohidrates() * quantityPercent;
            proteinsSum += alimentModel.getProteins() * quantityPercent;
            fibersSum += alimentModel.getFibers() * quantityPercent;
            vitASum += alimentModel.getVitA() * quantityPercent;
            vitBSum += alimentModel.getVitB() * quantityPercent;
            vitCSum += alimentModel.getVitC() * quantityPercent;
            magneziumSum += alimentModel.getMagnezium() * quantityPercent;
            sodiumSum += alimentModel.getSodium() * quantityPercent;
        }
    }

    public MealSummary saveOrUpdateMealSummary(Meal meal, String alimentName, int quantity){
        Optional<MealSummary> existingMealSummaryOptional = mealSummaryRepository.findByMeal_MealId(meal.getMealId());
        AlimentModel alimentModel = alimentRepository.findByAlimentName(alimentName);
        double quantityPercent= (double) quantity/100;
        if (existingMealSummaryOptional.isPresent()) {
            MealSummary existingMealSummary = existingMealSummaryOptional.get();
            existingMealSummary.setKcal(existingMealSummary.getKcal()+ (int)(alimentModel.getKcal()*quantityPercent));
            existingMealSummary.setCarbohidrates(existingMealSummary.getCarbohidrates() + alimentModel.getCarbohidrates()*quantityPercent);
            existingMealSummary.setProteins(existingMealSummary.getProteins() + alimentModel.getProteins()*quantityPercent);
            existingMealSummary.setFibers(existingMealSummary.getFibers() + alimentModel.getFibers()*quantityPercent);
            existingMealSummary.setVitA(existingMealSummary.getVitA()+alimentModel.getVitA()*quantityPercent);
            existingMealSummary.setVitB(existingMealSummary.getVitB()+alimentModel.getVitB()*quantityPercent);
            existingMealSummary.setVitC(existingMealSummary.getVitC()+alimentModel.getVitC()*quantityPercent);
            existingMealSummary.setMagnezium(existingMealSummary.getMagnezium()+alimentModel.getMagnezium()*quantityPercent);
            existingMealSummary.setSodium(existingMealSummary.getSodium()+alimentModel.getSodium()*quantityPercent);

            return mealSummaryRepository.save(existingMealSummary);
        } else {
            // If the row doesn't exist, create a new one
            MealSummary newMealSummary = new MealSummary();
            newMealSummary.setMeal(meal);
            newMealSummary.setKcal((int)(alimentModel.getKcal()*quantityPercent));
            newMealSummary.setCarbohidrates(alimentModel.getCarbohidrates()*quantityPercent);
            newMealSummary.setProteins(alimentModel.getProteins()*quantityPercent);
            newMealSummary.setFibers(alimentModel.getFibers()*quantityPercent);
            newMealSummary.setVitA(alimentModel.getVitA()*quantityPercent);
            newMealSummary.setVitB(alimentModel.getVitB()*quantityPercent);
            newMealSummary.setVitC(alimentModel.getVitC()*quantityPercent);
            newMealSummary.setMagnezium(alimentModel.getMagnezium()*quantityPercent);
            newMealSummary.setSodium(alimentModel.getSodium()*quantityPercent);

            return mealSummaryRepository.save(newMealSummary);

        }
 }
}
