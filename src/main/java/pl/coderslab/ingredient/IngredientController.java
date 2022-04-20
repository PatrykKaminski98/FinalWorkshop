package pl.coderslab.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private final HistoryService historyService;


    public IngredientController(IngredientService ingredientService, ProductService productService, MealNutritionService mealNutritionService, HistoryService historyService) {
        this.ingredientService = ingredientService;
        this.productService = productService;
        this.mealNutritionService = mealNutritionService;
        this.historyService = historyService;
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @RequestMapping("/delete/{ingredient_id}")
    public String deleteIngr(@PathVariable long ingredient_id)
    {
        LocalDate date = ingredientService.dateFromIngredient(ingredientService.findById(ingredient_id));
        ingredientService.delete(ingredient_id);
        return "redirect:/mealNutritions/table/" +date.toString();
    }

    @GetMapping("/edit/{ingredient_id}")
    public String editIngr(Model model, @PathVariable long ingredient_id)
    {
        Ingredient ingredient = ingredientService.findById(ingredient_id);
        model.addAttribute("ingredient", ingredient);
        return "/mealNutritions/edit_ingredient";
    }

    @PostMapping("/edit_ing")
    public String editIngrPost(Ingredient ingredient, @RequestParam long productId)
    {
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        ingredient.setProduct(productService.getById(productId));
        ingredientService.editIngredient(ingredient);
        return "redirect:/mealNutritions/table/" + date.toString();
    }

    @GetMapping("/addToMeal/{id}")
    public String addToMeal(Model model, @PathVariable long id)
    {
        model.addAttribute("mealId", id);
        model.addAttribute("ingredient", new Ingredient());
        return "/mealNutritions/add_ingredient";
    }

    @PostMapping("/addToMeal")
    public String addToMealPost(Ingredient ingredient, @RequestParam long mealId)
    {
        mealNutritionService.addIngredientToMeal(mealId, ingredient);
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        return "redirect:/mealNutritions/table/" + date.toString();
    }


}
