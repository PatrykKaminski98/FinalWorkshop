package pl.coderslab.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class MealConverter implements Converter<Long, Meal> {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Meal convert(Long source) {
        return mealRepository.findMealById(source);
    }
}
