package pl.coderslab.mealNutrition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.ingredient.IngredientRepository;
import pl.coderslab.product.Product;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealNutritionServiceTest {
    private MealNutritionService underTest;

    private MealNutritionRepository mealNutritionRepository;
    private IngredientRepository ingredientRepository;

    @BeforeEach
    public void setUp() {
         mealNutritionRepository = mock(MealNutritionRepository.class);
         ingredientRepository = mock(IngredientRepository.class);
         underTest = new MealNutritionService(mealNutritionRepository, ingredientRepository);
    }


    @Test
    void shouldCalculateMealNutritions() {
        //given
        List<Ingredient> ingredients = prepareListOfIngredients();
        MealNutrition expected = new MealNutrition();
        expected.setProteins(40);
        expected.setCarbohydrates(55);
        expected.setFats(45);
        expected.setKilocalories(1100);
        expected.setIngredients(ingredients);

        MealNutrition mealNutrition = new MealNutrition();
        mealNutrition.setIngredients(ingredients);

        //when
        MealNutrition actual = underTest.calculateMealNutritions(mealNutrition);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteIngredient() {
        //given
        MealNutrition mealNutrition = prepareMealNutritionWithIngredient();
        Ingredient ingredient = mealNutrition.getIngredients().get(0);
        given(mealNutritionRepository.findMealNutritionByIngredientsContains(ingredient)).willReturn(mealNutrition);
        //when
        underTest.deleteIngredient(ingredient);
        //then
        assertThat(mealNutrition.getIngredients().isEmpty());


    }



    List<Ingredient> prepareListOfIngredients(){
        List<Ingredient> listOfIngredients = new ArrayList<>();

        Ingredient firstIngredient = new Ingredient();
        Product firstProduct =  new Product();
        firstProduct.setProteins(10);
        firstProduct.setCarbohydrates(15);
        firstProduct.setFats(25);
        firstProduct.setKilocalories(300);
        firstIngredient.setProduct(firstProduct);
        firstIngredient.setProductQuantity(100);
        listOfIngredients.add(firstIngredient);

        Ingredient secondIngredient = new Ingredient();
        Product secondProduct = new Product();
        secondProduct.setProteins(Math.round(15));
        secondProduct.setCarbohydrates(20);
        secondProduct.setFats(10);
        secondProduct.setKilocalories(400);
        secondIngredient.setProduct(secondProduct);
        secondIngredient.setProductQuantity(200);
        listOfIngredients.add(secondIngredient);

        return listOfIngredients;
    }

    Product prepareExampleProduct(){
        Product product = new Product();
        product.setKilocalories(100);
        product.setProteins(50);
        product.setCarbohydrates(30);
        product.setFats(20);
        product.setName("exampleProduct");
        return product;
    }

    Ingredient prepareExampleIngredient(){
        Ingredient ingredient = new Ingredient();
        ingredient.setProduct(prepareExampleProduct());
        ingredient.setProductQuantity(100);
        return ingredient;
    }

    MealNutrition prepareMealNutritionWithIngredient(){
        MealNutrition mealNutrition = new MealNutrition();
        mealNutrition.setIngredients(new ArrayList<>());
        mealNutrition.addIngredient(prepareExampleIngredient());
        return mealNutrition;
    }



}