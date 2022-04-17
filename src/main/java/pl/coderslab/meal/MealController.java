package pl.coderslab.meal;

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
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;
import pl.coderslab.product.ProductService;
import pl.coderslab.user.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/meal")
@Slf4j
public class MealController {

    private LocalDate date;

    private final Meal_Service meal_service;
    private final IngredientService ingredientService;

    private final HistoryService historyService;
    private final MealNutritionService mealNutritionService;
    private final ProductService productService;

    private final HistoryRepository historyRepository;

    public MealController(Meal_Service meal_service, IngredientService ingredientService, HistoryService historyService, MealNutritionService mealNutritionService, ProductService productService, HistoryRepository historyRepository) {
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

    @GetMapping("/add")
    public String addStart(Model model)
    {
        List<String> meals = meal_service.findAllMealNames();
        model.addAttribute("meals", meals);
        model.addAttribute("mealNutrition", new MealNutrition());
        return "/meals/add_meal";
    }
    @PostMapping("/add")
    public String addMealPost(MealNutrition mealNutrition, HttpSession session)
    {
        session.setAttribute("mealNutrition", mealNutrition);
        return "redirect:/meal/ingredient";
    }
    @GetMapping("/ingredient")
    public String ingredientGet(Model model, HttpSession session)
    {
        List<Ingredient> ingredients = (List<Ingredient>) session.getAttribute("ingredients");
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("ingredients", ingredients);
        return "/meals/add_ingredients";
    }

    @PostMapping("/ingredient")
    public String ingredientPost(HttpSession session, @Valid Ingredient ingredient, BindingResult result, MealNutrition mealNutrition)
    {
        if(result.hasErrors()){
            return "/meals/add_ingredients";
        }

        List<Ingredient> ingredients;
        if(session.getAttribute("ingredients") == null){
            ingredients = new LinkedList<>(Arrays.asList(ingredientService.save(ingredient)));

        } else {
            ingredients = (List<Ingredient>) session.getAttribute("ingredients");
            ingredients.add(ingredientService.save(ingredient));

        }

        session.setAttribute("ingredients", ingredients);
        return "redirect:/meal/ingredient";
    }

    @GetMapping("/add_meal")
    public String addMealPost(HttpSession session)
    {
        MealNutrition mealNutrition = (MealNutrition) session.getAttribute("mealNutrition");     //
        List<Ingredient> ingredients = (List<Ingredient>) session.getAttribute("ingredients");   //  getting data

        History history = new History();                          // setting history
        history.setDate(date);                          //
        history.setUser((User)session.getAttribute("user"));    //
        History save = historyService.save(history);               //and saving history


        mealNutrition.setIngredients(ingredients);                      //setting mealNutrition
        mealNutritionService.getMealNutritionFromIngredients(mealNutrition);
        mealNutrition.setHistory(save);
        mealNutritionService.save(mealNutrition);                       // and save it

        session.removeAttribute("ingredients");                     // clean session
        session.removeAttribute("mealNutrition");                   //
        return "redirect:/meal/table/" + date.toString();
    }

    @RequestMapping("/table")
    public String table(Model model, HttpSession session)
    {
        date = LocalDate.now();
        System.out.println(date);
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", LocalDate.now());
        return "/meals/your_eating";
    }

    @RequestMapping("/table/{dateString}")
    public String table(Model model, HttpSession session, @PathVariable String dateString)
    {
        date = LocalDate.parse(dateString);
        System.out.println(date);
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user, date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/meals/your_eating";
    }

    @RequestMapping("/table/previous")
    public String tablePrevious(Model model, HttpSession session)
    {
        date = date.minus(Period.ofDays(1));
        System.out.println(date);
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/meals/your_eating";
    }

    @RequestMapping("/table/next")
    public String tableNext(Model model, HttpSession session)
    {
        date = date.plus(Period.ofDays(1));
        System.out.println(date);
        User user = (User)session.getAttribute("user");
        List<MealNutrition> mealNutritionsOfDay = meal_service.getMealNutritionsOfDay(user,date);
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/meals/your_eating";
    }
}
