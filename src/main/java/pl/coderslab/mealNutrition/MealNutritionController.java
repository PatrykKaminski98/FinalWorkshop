package pl.coderslab.mealNutrition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.account.appUser.CurrentUser;
import pl.coderslab.history.History;
import pl.coderslab.history.HistoryService;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.meal.Meal;
import pl.coderslab.meal.MealService;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductService;
import pl.coderslab.account.appUser.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Controller
@RequestMapping("/mealNutritions")
@Slf4j
public class MealNutritionController {

    private LocalDate date;
    private final MealService mealService;

    private final HistoryService historyService;
    private final MealNutritionService mealNutritionService;
    private final ProductService productService;

    public MealNutritionController(MealService mealService, HistoryService historyService, MealNutritionService mealNutritionService, ProductService productService) {
        this.mealService = mealService;
        this.historyService = historyService;
        this.mealNutritionService = mealNutritionService;
        this.productService = productService;
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @ModelAttribute("meals")
    public List<Meal> meals(){return mealService.findAll();}

    @GetMapping("/add")
    public String addStart(Model model)
    {
        model.addAttribute("mealNutrition", new MealNutrition());
        return "/mealNutritions/add_meal";
    }
    @PostMapping("/add")
    public String addMealPost(MealNutrition mealNutrition)
    {
        mealNutritionService.save(mealNutrition);
        return "redirect:/mealNutritions/ingredient";
    }
    @GetMapping("/ingredient")
    public String ingredientGet(Model model)
    {
        MealNutrition mealNutrition =mealNutritionService.findLast();
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("mealNutrition", mealNutrition);
        return "mealNutritions/add_ingredients";
    }

    @PostMapping("/ingredient")
    public String ingredientPost(@Valid Ingredient ingredient, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            return "mealNutritions/add_ingredients";
        }
        MealNutrition mealNutrition =mealNutritionService.findLast();
        model.addAttribute("mealNutrition", mealNutritionService.addIngredient(ingredient, mealNutrition));
        return "redirect:/mealNutritions/ingredient";
    }

    @GetMapping("/addMeal")
    public String addMeal(@AuthenticationPrincipal CurrentUser currentUser)
    {
        MealNutrition mealNutrition =mealNutritionService.findLast();

        User user = currentUser.getUser();
        History history = historyService.createAndSaveHistory(date, user);

        mealNutritionService.getMealNutritionFromIngredients(mealNutrition); //setting mealNutrition
        mealNutrition.setHistory(history);
        mealNutritionService.save(mealNutrition);                       // and save it

        return "redirect:/mealNutritions/table/" + date.toString();
    }

    @RequestMapping("/table")
    public String table(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        mealNutritionService.deleteAllWithoutHistory();
        date = LocalDate.now();
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealService.getMealNutritionsOfDay(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/{dateString}")
    public String table(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable String dateString)
    {
        date = LocalDate.parse(dateString);
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealService.getMealNutritionsOfDay(user, date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/previous")
    public String tablePrevious(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        date = date.minus(Period.ofDays(1));
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealService.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/next")
    public String tableNext(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        date = date.plus(Period.ofDays(1));
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealService.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }
}
