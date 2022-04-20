package pl.coderslab.ingredient;


import org.springframework.stereotype.Service;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionRepository;
import pl.coderslab.mealNutrition.MealNutritionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final HistoryRepository historyRepository;

    private final MealNutritionRepository mealNutritionRepository;

    private final MealNutritionService mealNutritionService;


    public IngredientService(IngredientRepository ingredientRepository, HistoryRepository historyRepository, MealNutritionRepository mealNutritionRepository, MealNutritionService mealNutritionService) {
        this.ingredientRepository = ingredientRepository;
        this.historyRepository = historyRepository;
        this.mealNutritionRepository = mealNutritionRepository;
        this.mealNutritionService = mealNutritionService;
    }

    public void delete(long ingredients_id){
        Ingredient ingredient = ingredientRepository.findById(ingredients_id);
        mealNutritionService.deleteIngredient(ingredient);
        ingredientRepository.deleteIngredientById(ingredients_id);
    }

    public Ingredient findById(long ingredient_id){
        return ingredientRepository.findById(ingredient_id);
    }

    void editIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        MealNutrition mealNutrition = mealNutritionService.getMealNutritionByIngredient(ingredient);
        mealNutrition = mealNutritionService.getMealNutritionFromIngredients(mealNutrition);
        mealNutritionService.save(mealNutrition);
    }


    public List<Ingredient> getAllIngredientsByMealNutrition(MealNutrition mealNutrition){
        return ingredientRepository.findAllByMealNutritionQuery(mealNutrition);
    }

    public List<List<Ingredient>> getListsOfIngredients(List<MealNutrition> mealNutritions){
        List<List<Ingredient>> result = new ArrayList<>();
        mealNutritions.forEach(meal -> {
            result.add(getAllIngredientsByMealNutrition(meal));
        });
        return result;
    }

    public LocalDate dateFromIngredient(Ingredient ingredient){
        MealNutrition mealNutrition = mealNutritionRepository.findMealNutritionByIngredients(ingredient);
        return mealNutrition.getHistory().getDate();
    }



    public Ingredient save(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }




}
