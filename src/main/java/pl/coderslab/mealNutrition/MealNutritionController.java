package pl.coderslab.mealNutrition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.history.History;
import pl.coderslab.history.HistoryRepository;
import pl.coderslab.history.HistoryService;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.ingredient.IngredientService;
import pl.coderslab.meal.Meal;
import pl.coderslab.meal.Meal_Service;
import pl.coderslab.mealNutrition.MealNutrition;
import pl.coderslab.mealNutrition.MealNutritionService;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductService;
import pl.coderslab.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Controller
@RequestMapping("/mealNutritions")
@Slf4j
public class MealNutritionController {

    private LocalDate date;
    private final Meal_Service meal_service;
    private final IngredientService ingredientService;

    private final HistoryService historyService;
    private final MealNutritionService mealNutritionService;
    private final ProductService productService;

    private final HistoryRepository historyRepository;

    public MealNutritionController(Meal_Service meal_service, IngredientService ingredientService, HistoryService historyService, MealNutritionService mealNutritionService, ProductService productService, HistoryRepository historyRepository) {
        this.meal_service = meal_service;
        this.ingredientService = ingredientService;
        this.historyService = historyService;
        this.mealNutritionService = mealNutritionService;
        this.productService = productService;
        this.historyRepository = historyRepository;
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @ModelAttribute("meals")
    public List<Meal> meals(){return meal_service.findAll();}

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
        return "/mealNutritions/add_ingredients";
    }

    @PostMapping("/ingredient")
    public String ingredientPost(@Valid Ingredient ingredient, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            return "/mealNutritions/add_ingredients";
        }
        MealNutrition mealNutrition =mealNutritionService.findLast();
        model.addAttribute("mealNutrition", mealNutritionService.addIngredient(ingredient, mealNutrition));
        return "redirect:/mealNutritions/ingredient";
    }

    @GetMapping("/add_meal")
    public String addMealPost(HttpSession session)
    {
        MealNutrition mealNutrition =mealNutritionService.findLast();

        History history = new History();                          // setting history
        history.setDate(date);                                       //
        history.setUser((User)session.getAttribute("user"));    //
        history = historyService.save(history);               //and saving history


        mealNutritionService.getMealNutritionFromIngredients(mealNutrition); //setting mealNutrition
        mealNutrition.setHistory(history);
        mealNutritionService.save(mealNutrition);                       // and save it

        return "redirect:/mealNutritions/table/" + date.toString();
    }

    @RequestMapping("/table")
    public String table(Model model, HttpSession session)
    {
        mealNutritionService.deleteAllWithoutHistory();
        date = LocalDate.now();
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", LocalDate.now());
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/{dateString}")
    public String table(Model model, HttpSession session, @PathVariable String dateString)
    {
        date = LocalDate.parse(dateString);
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user, date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/previous")
    public String tablePrevious(Model model, HttpSession session)
    {
        date = date.minus(Period.ofDays(1));
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/next")
    public String tableNext(Model model, HttpSession session)
    {
        date = date.plus(Period.ofDays(1));
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }
}
