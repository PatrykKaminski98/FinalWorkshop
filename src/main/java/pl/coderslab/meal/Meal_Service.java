package pl.coderslab.meal;

import org.springframework.stereotype.Service;
import pl.coderslab.history.History;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.ingredient.IngredientService;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    public List<Meal> findAll(){
        return mealRepository.findAll();
    }

    public void deleteById(long id){
        mealRepository.delete(findById(id));
    }

    public Meal findById(long id){
        return mealRepository.findMealById(id);
    }

    public void deleteProduct(long mealId, long productId){
        mealRepository.deleteByIdAndProductsIdQuery(mealId, productId);
    }

    public void addProduct(long mealId, long productId){
        mealRepository.addProductToMeal(mealId, productId);
    }






}




