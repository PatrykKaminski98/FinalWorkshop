package pl.coderslab.ingredient;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionRepository;
import pl.coderslab.mealNutrition.MealNutritionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final MealNutritionRepository mealNutritionRepository;

    private final MealNutritionService mealNutritionService;

    public void delete(long ingredientId){
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        mealNutritionService.deleteIngredient(ingredient);
        ingredientRepository.deleteIngredientById(ingredientId);
    }

    public Ingredient findById(long ingredientId){
        return ingredientRepository.findById(ingredientId);
    }

    void editIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        MealNutrition mealNutrition = mealNutritionService.getMealNutritionByIngredient(ingredient);
        mealNutritionService.getMealNutritionFromIngredients(mealNutrition);
        mealNutritionService.save(mealNutrition);
    }

    public LocalDate dateFromIngredient(Ingredient ingredient){
        long mealNutritionId = mealNutritionRepository.findMealNutritionIdByIngredientId(ingredient.getId());
        MealNutrition mealNutrition = mealNutritionRepository.findMealNutritionById(mealNutritionId);
        return mealNutrition.getHistory().getDate();
    }

    public Ingredient save(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }




}
