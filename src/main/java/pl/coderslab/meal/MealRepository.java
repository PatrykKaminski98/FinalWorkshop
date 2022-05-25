package pl.coderslab.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.product.Product;

import javax.transaction.Transactional;


public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findMealById(long id);
}
