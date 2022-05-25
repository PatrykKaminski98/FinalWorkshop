package pl.coderslab.ingredient;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionRepository;
import pl.coderslab.mealNutrition.MealNutritionService;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final MealNutritionRepository mealNutritionRepository;

    private final MealNutritionService mealNutritionService;

    void editIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        MealNutrition mealNutrition = mealNutritionRepository.findMealNutritionByIngredientsContains(ingredient);
        mealNutritionService.calculateMealNutritions(mealNutrition);
        mealNutritionRepository.save(mealNutrition);
    }

    public LocalDate dateFromIngredient(Ingredient ingredient){
        return mealNutritionRepository.findMealNutritionByIngredientsContains(ingredient).getDate();
    }




}
