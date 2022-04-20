package pl.coderslab.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.mealNutrition.MealNutrition;

import javax.transaction.Transactional;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Transactional
    void deleteIngredientById(long id);

    Ingredient findById(long id);

    @Query(value = "SELECT mn.ingredients from MealNutrition mn WHERE mn = ?1")
    List<Ingredient> findAllByMealNutritionQuery(MealNutrition mealNutrition);
}
