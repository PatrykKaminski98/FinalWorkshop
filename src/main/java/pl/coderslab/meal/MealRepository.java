package pl.coderslab.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.product.Product;


public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal findById(long id);

    @Query(value = "insert into meal_products values (?1, ?2)", nativeQuery = true)
    void addProductQuery(long mealId, long productId);
}
