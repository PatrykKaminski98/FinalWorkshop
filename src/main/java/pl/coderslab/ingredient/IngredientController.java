package pl.coderslab.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.history.HistoryService;
import pl.coderslab.product.Product;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final HistoryService historyService;


    public IngredientController(IngredientService ingredientService, HistoryService historyService) {
        this.ingredientService = ingredientService;
        this.historyService = historyService;
    }

    @RequestMapping("/delete/{ingredient_id}")
    public String deleteIngr(@PathVariable long ingredient_id)
    {
        LocalDate date = ingredientService.dateFromIngredient(ingredientService.findById(ingredient_id));
        ingredientService.delete(ingredient_id);
        return "redirect:/meal/table/" +date.toString();
    }

    @GetMapping("/edit/{ingredient_id}")
    public String editIngr(Model model, @PathVariable long ingredient_id)
    {
        Ingredient ingredient = ingredientService.findById(ingredient_id);
        System.out.println("metoda get" + ingredient);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("product", ingredient.getProduct());
        return "/meals/edit_ingredient";
    }

    @PostMapping("/edit_ing")
    public String editIngrPost(Ingredient ingredient, Product product)
    {
        System.out.println(product.toString());
        LocalDate date = ingredientService.dateFromIngredient(ingredient);
        System.out.println("metoda post" + ingredient);
        ingredientService.editIngredient(ingredient);
        return "redirect:/meal/table/" + date.toString();
    }


}
