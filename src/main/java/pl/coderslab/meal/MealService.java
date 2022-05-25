package pl.coderslab.meal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;


@Service
@AllArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    public void deleteProduct(long mealId, long productId){
        Meal meal = mealRepository.findMealById(mealId);
        Product product = productRepository.findProductById(productId);
        meal.removeProduct(product);
        mealRepository.save(meal);
    }

    public void addProduct(long mealId, long productId){
        Meal meal = mealRepository.findMealById(mealId);
        Product product = productRepository.findProductById(productId);
        meal.addProduct(product);
        mealRepository.save(meal);
    }
}




