package pl.coderslab.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.history.History;
import pl.coderslab.ingredient.Ingredient;

import javax.transaction.Transactional;
import java.util.List;

public interface MealNutritionRepository extends JpaRepository<MealNutrition, Long> {

    @Query("select mn from MealNutrition mn where mn.history in (:hList)")
    List<MealNutrition> findAllByHistoryListQuery(@Param("hList")List<History> historyList);

    public MealNutrition findMealNutritionByHistory(History history);

    public MealNutrition findMealNutritionByIngredients(Ingredient ingredient);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meal_nutrition_ingredients WHERE meal_nutrition_id = ?1 AND ingredients_id = ?2", nativeQuery = true)
    public void deleteingredientQuery(long mealId, long ingrId);
}
