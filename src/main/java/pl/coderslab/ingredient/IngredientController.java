package pl.coderslab.ingredient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ingredient")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    private final IngredientRepository ingredientRepository;
    private final MealNutritionService mealNutritionService;

    private final ProductRepository productRepository;

    @ModelAttribute("products")
    public List<Product> products(){
        return productRepository.findAll();
    }

    @RequestMapping("/delete/{ingredientId}")
    public String deleteIngr(@PathVariable long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        mealNutritionService.deleteIngredient(ingredient);
        return "redirect:/mealNutritions/table/" +date.toString();
    }

    @GetMapping("/edit/{ingredientId}")
    public String editIngr(Model model, @PathVariable long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "/mealNutritions/edit_ingredient";
    }

    @PostMapping("/edit_ing")
    public String editIngrPost(Ingredient ingredient, @RequestParam long productId) {
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        ingredient.setProduct(productRepository.getById(productId));
        ingredientService.editIngredient(ingredient);
        return "redirect:/mealNutritions/table/" + date.toString();
    }

    @GetMapping("/addToMeal/{mealId}")
    public String addToMeal(Model model, @PathVariable long mealId) {
        model.addAttribute("mealId", mealId);
        model.addAttribute("ingredient", new Ingredient());
        return "/mealNutritions/add_ingredient";
    }

    @PostMapping("/addToMeal")
    public String addToMealPost(Ingredient ingredient, @RequestParam long mealId) {
        mealNutritionService.addIngredient(mealId, ingredient);
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        return "redirect:/mealNutritions/table/" + date.toString();
    }


}
