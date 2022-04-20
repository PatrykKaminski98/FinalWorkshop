package pl.coderslab.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.history.HistoryService;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.product.Product;

import java.time.LocalDate;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final MealNutritionService mealNutritionService;
    private final HistoryService historyService;


    public IngredientController(IngredientService ingredientService, MealNutritionService mealNutritionService, HistoryService historyService) {
        this.ingredientService = ingredientService;
        this.mealNutritionService = mealNutritionService;
        this.historyService = historyService;
    }

    @RequestMapping("/delete/{ingredient_id}")
    public String deleteIngr(@PathVariable long ingredient_id)
    {
        LocalDate date = ingredientService.dateFromIngredient(ingredientService.findById(ingredient_id));
        ingredientService.delete(ingredient_id);
        return "redirect:/mealNutrition/table/" +date.toString();
    }

    @GetMapping("/edit/{ingredient_id}")
    public String editIngr(Model model, @PathVariable long ingredient_id)
    {
        Ingredient ingredient = ingredientService.findById(ingredient_id);
        System.out.println("metoda get" + ingredient);
        model.addAttribute("ingredient", ingredient);
        return "/mealNutritions/edit_ingredient";
    }

    @PostMapping("/edit_ing")
    public String editIngrPost(Ingredient ingredient)
    {
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        System.out.println("metoda post" + ingredient);
        ingredientService.editIngredient(ingredient);
        return "redirect:/mealNutrition/table/" + date.toString();
    }


}
