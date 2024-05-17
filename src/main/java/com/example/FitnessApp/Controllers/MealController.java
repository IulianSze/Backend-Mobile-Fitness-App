package com.example.FitnessApp.Controllers;

import com.example.FitnessApp.DTOs.*;
import com.example.FitnessApp.Models.Meal;
import com.example.FitnessApp.Models.MealFoodItem;
import com.example.FitnessApp.Models.MealSummary;
import com.example.FitnessApp.Models.MealType;
import com.example.FitnessApp.Repositories.MealFoodItemRepository;
import com.example.FitnessApp.Services.MealFoodItemService;
import com.example.FitnessApp.Services.MealService;
import com.example.FitnessApp.Services.MealSummaryService;
import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/auth/meals")
@CrossOrigin("*")
public class MealController {
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealService mealService;

    @Autowired
    private MealSummaryService mealSummaryService;
    @Autowired
    private MealFoodItemService mealFoodItemService;

//    @Transactional
//    @PostMapping("/addmeal")
//    public ResponseEntity<MealAndSummaryDTO> saveMeal(@RequestBody MealAndSummaryDTO mealAndSummaryDTO) {
//        logger.info("Received request to save meal: {}", mealAndSummaryDTO);
//        if (mealAndSummaryDTO == null) {
//            logger.error("MealAndSummaryDTO is null");
//            return ResponseEntity.badRequest().build();
//        }
//
//        MealDTO mealDto = mealAndSummaryDTO.getMealDto();
//        MealSummaryDTO mealSummaryDTO = mealAndSummaryDTO.getMealSummaryDto();
//        List<MealFoodItemDTO> mealFoodItemsDTOList= mealAndSummaryDTO.getMealFoodItemsDTO();
//
//        Meal savedMeal = mealService.saveMeal(mealDto);
//        mealSummaryDTO.setMeal(savedMeal);
//        MealSummary savedMealSummary= mealSummaryService.saveMealSummary(mealSummaryDTO);
//        for(MealFoodItemDTO mealFoodItemDTO: mealFoodItemsDTOList) {
//            mealFoodItemDTO.setMealId(savedMeal.getMealId());
//            MealFoodItem mealFoodItem = mealFoodItemService.saveMealFoodItem(mealFoodItemDTO);
//        }
//        return new ResponseEntity<>(mealAndSummaryDTO, HttpStatus.CREATED);
//    }

    @PostMapping("/logmeal")
    public ResponseEntity<Meal> logMeal(@RequestBody MealDTO mealDTO) {
        if (mealDTO == null) {
            logger.error("Meal item is null");
            return ResponseEntity.badRequest().build();
        }
        Meal savedMeal = mealService.saveMeal(mealDTO);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);

    }

    @PostMapping("/logaliment")
    public ResponseEntity<MealFoodItem> logAliment(@RequestBody MealFoodItemDTO mealFoodItemDTO) {
        if (mealFoodItemDTO == null) {
            logger.error("Meal and food item are null");
            return ResponseEntity.badRequest().build();
        }
        MealFoodItem savedAliment = mealFoodItemService.saveMealFoodItem(mealFoodItemDTO);
        return new ResponseEntity<>(savedAliment, HttpStatus.CREATED);
    }
    @PatchMapping("/updatealiment")
    public ResponseEntity<MealFoodItem> updateMealFoodItemQuantity(@RequestBody MealFoodItemDTO mealFoodItemDTO) {
        MealFoodItem updatedItem = mealFoodItemService.updateMealFoodItem(mealFoodItemDTO);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @PatchMapping("/savesummary")
    public ResponseEntity<MealSummary> saveOrUpdateSummary(@RequestBody Meal meal, @RequestParam String alimentName, @RequestParam int quantity) {
        if (meal == null) {
            logger.error("Meal is null");
            return ResponseEntity.badRequest().build();
        }
        MealSummary savedMealSummary = mealSummaryService.saveOrUpdateMealSummary(meal, alimentName, quantity);
        return new ResponseEntity<>(savedMealSummary, HttpStatus.CREATED);
    }
    @GetMapping("/dailyinfo")
    public MealSummary dailyInfo(@RequestParam int userId, @RequestParam LocalDate date){
        return mealService.computeAndGetAllDayInfoBasedOnMeals(userId, date);
    }


    @GetMapping("/getsummary")
    public ResponseEntity<MealSummary> getMealSummary(@RequestParam int mealId){
        Optional<MealSummary> existingMealSummary = mealSummaryService.getMealSummary(mealId);
        return new ResponseEntity<>(existingMealSummary.get(), HttpStatus.OK);
    }
    @GetMapping("/alimentsfrommeal")
    public ResponseEntity<List<MealFoodItem>> getAlimentsFromMeal(@RequestParam int mealId){
        return new ResponseEntity<>(mealFoodItemService.getAlimentsFromMeal(mealId), HttpStatus.OK);
    }
    @GetMapping("/specificmeal")
    public ResponseEntity<Meal> getMealForUserAndDateAndMealType(@RequestParam Integer userId, @RequestParam LocalDate date, @RequestParam MealType mealType){
        return new ResponseEntity<>(mealService.getMealForUserMealTypeAndDate(userId,mealType,date), HttpStatus.OK);
    }
    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getMealsForUserAndDate(@RequestBody RequestMealInfo request) {
        Integer userId = request.getUserId();
        LocalDate date = request.getDate();

        List<Meal> mealsList = mealService.getMealsForUserAndDate(userId, date);
        return new ResponseEntity<>(mealsList, HttpStatus.OK);
    }
    @GetMapping("/allinfo")
    public ResponseEntity<List<MealInfoDTO>> getMealInfoForUserAndDate(@RequestParam Integer userId, @RequestParam LocalDate date) {
        List<MealInfoDTO> mealInfoList = mealService.getMealInfoForUserAndDate(userId, date);
        return new ResponseEntity<>(mealInfoList, HttpStatus.OK);
    }
    @GetMapping("/logfoodinfo")
    public ResponseEntity<List<MealInfoRelevantForLogPageDTO>> getMealLogFoodInfoForUserAndDate(@RequestParam Integer userId, @RequestParam LocalDate date) {
        List<MealInfoRelevantForLogPageDTO> mealInfoList = mealService.getMealInfoForLogPage(userId, date);
        return new ResponseEntity<>(mealInfoList, HttpStatus.OK);
    }
}
