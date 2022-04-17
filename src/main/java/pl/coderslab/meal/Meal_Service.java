package pl.coderslab.meal;

import org.springframework.stereotype.Service;
import pl.coderslab.history.History;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.ingredient.IngredientService;
import pl.coderslab.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Meal_Service {

    private final HistoryRepository historyRepository;
    private final MealRepository mealRepository;
    private final IngredientService ingredientService;
    private final MealNutritionService mealNutritionService;

    public Meal_Service(HistoryRepository historyRepository, MealRepository mealRepository, IngredientService ingredientService, MealNutritionService mealNutritionService) {
        this.historyRepository = historyRepository;
        this.mealRepository = mealRepository;
        this.ingredientService = ingredientService;
        this.mealNutritionService = mealNutritionService;
    }


    public List<MealNutrition> getMealNutritionsOfDay(User user, LocalDate date){
        List<History> mealHistoryOfDay = historyRepository.findByUserAndDate(user, date);
        return mealNutritionService.getMealsNutritionsByHistory(mealHistoryOfDay);
    }

    List<String> findAllMealNames(){
        List<Meal> all = mealRepository.findAll();
        return all.stream()
                .map(meal -> meal.getName())
                .collect(Collectors.toList());
    }

/*
    private int getKcalOfDay(List<Meal_Nutrition> meal_nutritions){
        return meal_nutritions.stream()
                .map(meal_nutrition -> meal_nutrition.getKilocalories())
                .reduce(0,Integer::sum);
    }

    private int getProteinsOfDay(List<Meal_Nutrition> meal_nutritions){
        return meal_nutritions.stream()
                .map(meal_nutrition -> meal_nutrition.getProteins())
                .reduce(0,Integer::sum);
    }

    private int getCarbohydratesOfDay(List<Meal_Nutrition> meal_nutritions){
        return meal_nutritions.stream()
                .map(meal_nutrition -> meal_nutrition.getCarbohydrates())
                .reduce(0,Integer::sum);
    }

    private int getFatsOfDay(List<Meal_Nutrition> meal_nutritions){
        return meal_nutritions.stream()
                .map(meal_nutrition -> meal_nutrition.getFats())
                .reduce(0,Integer::sum);
    }
    */



}




