package pl.coderslab.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.account.appUser.UserService;
import pl.coderslab.history.HistoryService;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final ProductService productService;
    private final MealNutritionService mealNutritionService;


    public IngredientController(IngredientService ingredientService, ProductService productService, MealNutritionService mealNutritionService) {
        this.ingredientService = ingredientService;
        this.productService = productService;
        this.mealNutritionService = mealNutritionService;
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @RequestMapping("/delete/{ingredientId}")
    public String deleteIngr(@PathVariable long ingredientId) {
        LocalDate date = ingredientService.dateFromIngredient(ingredientService.findById(ingredientId));
        ingredientService.delete(ingredientId);
        return "redirect:/mealNutritions/table/" +date.toString();
    }

    @GetMapping("/edit/{ingredientId}")
    public String editIngr(Model model, @PathVariable long ingredientId) {
        Ingredient ingredient = ingredientService.findById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "/mealNutritions/edit_ingredient";
    }

    @PostMapping("/edit_ing")
    public String editIngrPost(Ingredient ingredient, @RequestParam long productId) {
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        ingredient.setProduct(productService.getById(productId));
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
        mealNutritionService.addIngredientToMeal(mealId, ingredient);
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        return "redirect:/mealNutritions/table/" + date.toString();
    }


}
