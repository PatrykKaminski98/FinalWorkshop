package pl.coderslab.meal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.history.History;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.ingredient.IngredientService;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.account.appUser.User;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MealService {

    private final HistoryRepository historyRepository;
    private final MealRepository mealRepository;
    private final MealNutritionService mealNutritionService;

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

    public void save(Meal meal){
        mealRepository.save(meal);
    }






}




