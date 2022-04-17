package pl.coderslab.meal;

import org.springframework.stereotype.Service;
import pl.coderslab.history.History;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.ingredient.IngredientRepository;

import java.util.List;

@Service
public class MealNutritionService {
    private final MealNutritionRepository mealNutritionRepository;
    private final IngredientRepository ingredientRepository;

    public MealNutritionService(MealNutritionRepository mealNutritionRepository, IngredientRepository ingredientRepository) {
        this.mealNutritionRepository = mealNutritionRepository;
        this.ingredientRepository = ingredientRepository;
    }


    public List<MealNutrition> getMealsNutritionsByHistory(List<History> historyList){
        return mealNutritionRepository.findAllByHistoryListQuery(historyList);
    }

    public MealNutrition getMealNutritionFromIngredients(MealNutrition mealNutrition){
        List<Ingredient> ingredients = mealNutrition.getIngredients();
        float kilocalories = 0;
        float proteins = 0;
        float carbohydrates = 0;
        float fats = 0;
        for (Ingredient ingredient : ingredients) {
            kilocalories += ingredient.getProduct().getKilocalories() * ingredient.getProductQuantity()/100;
            proteins += ingredient.getProduct().getProteins() * ingredient.getProductQuantity()/100;
            carbohydrates += ingredient.getProduct().getCarbohydrates() * ingredient.getProductQuantity()/100;
            fats += ingredient.getProduct().getFats() * ingredient.getProductQuantity()/100;
        }
        mealNutrition.setProteins(Math.round(proteins));
        mealNutrition.setCarbohydrates(Math.round(carbohydrates));
        mealNutrition.setFats(Math.round(fats));
        mealNutrition.setKilocalories(Math.round(kilocalories));
        return mealNutrition;
    }

    public MealNutrition save(MealNutrition mealNutrition){
        return mealNutritionRepository.save(mealNutrition);
    }

    public void deleteByHistory(History history){
        MealNutrition mealNutritionByHistory = mealNutritionRepository.findMealNutritionByHistory(history);
        mealNutritionRepository.delete(mealNutritionByHistory);
    }

    public void deleteIngredient(Ingredient ingredient){
        Long ingrId = ingredient.getId();
        MealNutrition mealNutrition = mealNutritionRepository.findMealNutritionByIngredients(ingredient);
        long mealId = mealNutrition.getId();
        mealNutritionRepository.deleteingredientQuery(mealId,ingrId);
    }
}
