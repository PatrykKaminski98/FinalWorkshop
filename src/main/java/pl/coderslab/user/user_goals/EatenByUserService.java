package pl.coderslab.user.user_goals;

import org.springframework.stereotype.Service;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.meal.Meal_Service;
import pl.coderslab.user.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class EatenByUserService {

    private final Meal_Service meal_service;

    public EatenByUserService(Meal_Service meal_service) {
        this.meal_service = meal_service;
    }

    public EatenByUser getEatenByUser(User user, LocalDate date) {
        List<MealNutrition> meal_nutritions = meal_service.getMealNutritionsOfDay(user, date);
        EatenByUser eatenByUser = new EatenByUser();

        eatenByUser.setKilocalories(
                meal_nutritions.stream()
                .map(meal_nutrition -> meal_nutrition.getKilocalories())
                .reduce(0, Integer::sum));

        eatenByUser.setProteins(
                meal_nutritions.stream()
                        .map(meal_nutrition -> meal_nutrition.getProteins())
                        .reduce(0, Integer::sum)
        );

        eatenByUser.setCarbohydrates(
                meal_nutritions.stream()
                        .map(meal_nutrition -> meal_nutrition.getCarbohydrates())
                        .reduce(0, Integer::sum)
        );

        eatenByUser.setFats(
                meal_nutritions.stream()
                        .map(meal_nutrition -> meal_nutrition.getFats())
                        .reduce(0, Integer::sum)
        );
        eatenByUser.setDate(date);
        return eatenByUser;
    }
}



