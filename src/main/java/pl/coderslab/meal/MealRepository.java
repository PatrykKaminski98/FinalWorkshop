package pl.coderslab.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.product.Product;

import javax.transaction.Transactional;


public interface MealRepository extends JpaRepository<Meal, Long> {

    Meal findMealById(long id);

    @Query(value = "insert into meal_products values (?1, ?2)", nativeQuery = true)
    void addProductQuery(long mealId, long productId);

    Meal findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meal_products WHERE meal_id =?1 AND products_id = ?2", nativeQuery = true)
    void deleteByIdAndProductsIdQuery(long mealId, long productId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO meal_products VALUES (?1, ?2)", nativeQuery = true)
    void addProductToMeal(long mealId, long productId);
}
