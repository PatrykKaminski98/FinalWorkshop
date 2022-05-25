package pl.coderslab.mealNutrition;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.ingredient.IngredientRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class MealNutritionService {
    private final MealNutritionRepository mealNutritionRepository;
    private final IngredientRepository ingredientRepository;

    public MealNutrition calculateMealNutritions(MealNutrition mealNutrition){
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

    public void deleteIngredient(Ingredient ingredient){
        MealNutrition mealNutrition = mealNutritionRepository.findMealNutritionByIngredientsContains(ingredient);
        mealNutrition.removeIngredient(ingredient);
        ingredientRepository.delete(ingredient);
        calculateMealNutritions(mealNutrition);
        mealNutritionRepository.save(mealNutrition);
    }

    public MealNutrition addIngredient(long mealId, Ingredient ingredient){
        MealNutrition mealNutrition = mealNutritionRepository.getById(mealId);
        ingredientRepository.saveAndFlush(ingredient);

        mealNutrition.addIngredient(ingredient);
        calculateMealNutritions(mealNutrition);

        return mealNutritionRepository.save(mealNutrition);
    }
}
