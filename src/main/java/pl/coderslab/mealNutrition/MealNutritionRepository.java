package pl.coderslab.mealNutrition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.history.History;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.mealNutrition.MealNutrition;

import javax.transaction.Transactional;
import java.util.List;

public interface MealNutritionRepository extends JpaRepository<MealNutrition, Long> {

    MealNutrition findMealNutritionById(long id);

    @Query("select mn from MealNutrition mn where mn.history in (:hList)")
    List<MealNutrition> findAllByHistoryListQuery(@Param("hList")List<History> historyList);

    MealNutrition findMealNutritionByHistory(History history);

    MealNutrition findMealNutritionByIngredients(Ingredient ingredient);

    @Query(value = "insert into meal_nutrition_ingredients (meal_nutrition_id, ingredients_id) VALUES (?1, ?2)",nativeQuery = true)
    void addIngredientQuery(long ingredient_id, long mealNutrition_id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meal_nutrition_ingredients WHERE meal_nutrition_id = ?1 AND ingredients_id = ?2", nativeQuery = true)
    public void deleteingredientQuery(long mealId, long ingrId);

    MealNutrition findFirstByOrderByIdDesc();

    @Modifying
    @Transactional
    void deleteAllByHistoryIsNull();
}
