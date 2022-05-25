package pl.coderslab.mealNutrition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import pl.coderslab.account.appUser.User;
import pl.coderslab.ingredient.Ingredient;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface MealNutritionRepository extends JpaRepository<MealNutrition, Long> {

    MealNutrition findMealNutritionById(long id);


    List<MealNutrition> findAllByUserAndDate(User user, LocalDate date);

    MealNutrition findMealNutritionByIngredientsContains(Ingredient ingredient);


    MealNutrition findFirstByOrderByIdDesc();

    @Modifying
    @Transactional
    void deleteAllByDateIsNull();
}
