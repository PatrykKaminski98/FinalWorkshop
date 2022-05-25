package pl.coderslab.account.appUser.userGoals;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.meal.MealService;
import pl.coderslab.account.appUser.User;
import pl.coderslab.mealNutrition.MealNutritionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EatenByUserService {

    private final MealService mealService;

    private final MealNutritionRepository mealNutritionRepository;

    public EatenByUser getEatenByUser(User user, LocalDate date) {
        List<MealNutrition> mealNutritions = mealNutritionRepository.findAllByUserAndDate(user, LocalDate.now());
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



