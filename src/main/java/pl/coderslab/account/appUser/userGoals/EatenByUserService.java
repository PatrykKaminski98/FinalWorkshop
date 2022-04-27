package pl.coderslab.account.appUser.userGoals;

import org.springframework.stereotype.Service;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.meal.MealService;
import pl.coderslab.account.appUser.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class EatenByUserService {

    private final MealService mealService;

    public EatenByUserService(MealService mealService) {
        this.mealService = mealService;
    }

    public EatenByUser getEatenByUser(User user, LocalDate date) {
        List<MealNutrition> mealNutritions = mealService.getMealNutritionsOfDay(user, date);
        EatenByUser eatenByUser = new EatenByUser();

        eatenByUser.setKilocalories(
                mealNutritions.stream()
                .map(mealNutrition -> mealNutrition.getKilocalories())
                .reduce(0, Integer::sum));

        eatenByUser.setProteins(
                mealNutritions.stream()
                        .map(mealNutrition -> mealNutrition.getProteins())
                        .reduce(0, Integer::sum)
        );

        eatenByUser.setCarbohydrates(
                mealNutritions.stream()
                        .map(mealNutrition -> mealNutrition.getCarbohydrates())
                        .reduce(0, Integer::sum)
        );

        eatenByUser.setFats(
                mealNutritions.stream()
                        .map(mealNutrition -> mealNutrition.getFats())
                        .reduce(0, Integer::sum)
        );
        eatenByUser.setDate(date);
        return eatenByUser;
    }
}



