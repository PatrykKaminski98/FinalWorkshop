package pl.coderslab.meal;

import lombok.Getter;
import pl.coderslab.ingredient.Ingredient;

import java.util.List;

@Getter
public class MealAndIngredients {
    private MealNutrition mealNutrition;
    private List<Ingredient> ingredients;

    public MealAndIngredients(MealNutrition mealNutrition, List<Ingredient> ingredients) {
        this.mealNutrition = mealNutrition;
        this.ingredients = ingredients;
    }
}


