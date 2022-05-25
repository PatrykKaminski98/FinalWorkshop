package pl.coderslab.mealNutrition;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.account.appUser.User;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
class MealNutritionRepositoryTest {

    @Autowired
    private MealNutritionRepository underTest;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void shouldFindMealNutritionById() {
        //given
        MealNutrition expected = new MealNutrition();
        underTest.save(expected);
        //when
        MealNutrition actualRecord = underTest.findMealNutritionById(1);
        //then
        assertEquals(expected, actualRecord);
    }

    @Test
    void shouldNotFindMealNutritionById() {
        //given
        //when
        MealNutrition actual = underTest.findMealNutritionById(1L);
        //then
        assertNull(actual);
    }

    @Test
    void shouldFindAllByUserAndDate() {
        //given
        User user = prepareAndSaveExampleUser();
        saveSomeMealNutritionsInMemory(user, LocalDate.now());

        //when
        List<MealNutrition> actual = underTest.findAllByUserAndDate(user, LocalDate.now());
        //then
        assertEquals(2, actual.size());
    }

    @Test
    void shouldFindMealNutritionByIngredientsContains() {
        //given
        MealNutrition mealNutritionContainingIngredient = prepareAndSaveMealNutritionWithIngredient();

        MealNutrition mealNutritionDoesntContainIngredient = new MealNutrition();
        entityManager.persistAndFlush(mealNutritionDoesntContainIngredient);

        //when
        MealNutrition actual = underTest.findMealNutritionByIngredientsContains(mealNutritionContainingIngredient.getIngredients().get(0));

        //then
        assertEquals(mealNutritionContainingIngredient, actual);
    }

    @Test
    void shouldFindFirstByOrderByIdDesc() {
        //given
        prepareAndSaveMealNutritionWithIngredient();
        prepareAndSaveMealNutritionWithIngredient();
        MealNutrition expected = prepareAndSaveMealNutritionWithIngredient();
        //when
        MealNutrition actual = underTest.findFirstByOrderByIdDesc();
        //then
        assertEquals(expected, actual);

    }

    @Test
    void deleteAllByDateIsNull() {
        //given
        List<MealNutrition> expected = saveSomeMealNutritionsInMemory(prepareAndSaveExampleUser(), LocalDate.now());
        prepareAndSaveMealNutritionWithIngredient();
        prepareAndSaveMealNutritionWithIngredient();
        //when
        underTest.deleteAllByDateIsNull();
        //then
        assertEquals(expected, underTest.findAll());
    }


    User prepareAndSaveExampleUser(){
        User user = new User();
        user.setEmail("patryk@interia.pl");
        return entityManager.persistAndFlush(user);
    }

    Product prepareAndSaveExampleProduct(){
        Product product = new Product();
        product.setKilocalories(100);
        product.setProteins(50);
        product.setCarbohydrates(30);
        product.setFats(20);
        product.setName("exampleProduct");
        return entityManager.persistAndFlush(product);
    }

    Ingredient prepareAndSaveExampleIngredient(){
        Ingredient ingredient = new Ingredient();
        ingredient.setProduct(prepareAndSaveExampleProduct());
        ingredient.setProductQuantity(100);
        return entityManager.persistAndFlush(ingredient);
    }

    MealNutrition prepareAndSaveMealNutritionWithIngredient(){
        MealNutrition mealNutrition = new MealNutrition();
        mealNutrition.setIngredients(new ArrayList<>());
        mealNutrition.addIngredient(prepareAndSaveExampleIngredient());
        return entityManager.persistAndFlush(mealNutrition);
    }


    List<MealNutrition> saveSomeMealNutritionsInMemory(User user, LocalDate date){
        ArrayList<MealNutrition> mealNutritionsList = new ArrayList<>();
        //first example
        MealNutrition mealNutrition1 = new MealNutrition();
        mealNutrition1.setDate(date);
        mealNutrition1.setUser(user);
        entityManager.persistAndFlush(mealNutrition1);
        mealNutritionsList.add(mealNutrition1);

        //second example
        MealNutrition mealNutrition2 = new MealNutrition();
        User user2 = new User();
        user2.setEmail("test@mail.com");
        mealNutrition2.setDate(date);
        mealNutrition2.setUser(user2);
        entityManager.persistAndFlush(mealNutrition2);
        mealNutritionsList.add(mealNutrition2);

        //third example
        MealNutrition mealNutrition3 = new MealNutrition();
        mealNutrition3.setDate(date);
        mealNutrition3.setUser(user);
        entityManager.persistAndFlush(mealNutrition3);
        mealNutritionsList.add(mealNutrition3);

        return mealNutritionsList;
    }



}