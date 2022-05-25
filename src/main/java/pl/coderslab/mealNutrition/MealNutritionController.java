package pl.coderslab.mealNutrition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.account.appUser.CurrentUser;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.meal.Meal;
import pl.coderslab.meal.MealRepository;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;
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

    private final MealRepository mealRepository;
    private final MealNutritionService mealNutritionService;
    private final ProductRepository productRepository;

    private final MealNutritionRepository mealNutritionRepository;

    public MealNutritionController(MealRepository mealRepository, MealNutritionService mealNutritionService, ProductRepository productRepository, MealNutritionRepository mealNutritionRepository) {
        this.mealRepository = mealRepository;
        this.mealNutritionService = mealNutritionService;
        this.productRepository = productRepository;
        this.mealNutritionRepository = mealNutritionRepository;
    }

    @ModelAttribute("products")
    public List<Product> products(){
        return productRepository.findAll();
    }

    @ModelAttribute("meals")
    public List<Meal> meals(){return mealRepository.findAll();}

    @GetMapping("/add")
    public String addStart(Model model)
    {
        model.addAttribute("mealNutrition", new MealNutrition());
        return "/mealNutritions/add_meal";
    }
    @PostMapping("/add")
    public String addMealPost(MealNutrition mealNutrition)
    {
        mealNutritionRepository.save(mealNutrition);
        return "redirect:/mealNutritions/ingredient";
    }
    @GetMapping("/ingredient")
    public String ingredientGet(Model model)
    {
        MealNutrition mealNutrition = mealNutritionRepository.findFirstByOrderByIdDesc();
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
        MealNutrition mealNutrition = mealNutritionRepository.findFirstByOrderByIdDesc();
        model.addAttribute("mealNutrition", mealNutritionService.addIngredient(mealNutrition.getId(), ingredient));
        return "redirect:/mealNutritions/ingredient";
    }

    @GetMapping("/addMeal")
    public String addMeal(@AuthenticationPrincipal CurrentUser currentUser)
    {
        MealNutrition mealNutrition = mealNutritionRepository.findFirstByOrderByIdDesc();
        mealNutritionService.calculateMealNutritions(mealNutrition); //setting mealNutrition
        User user = currentUser.getUser();
        mealNutrition.setUser(user);
        mealNutrition.setDate(date);
        mealNutritionRepository.save(mealNutrition);                       // and save it

        return "redirect:/mealNutritions/table/" + date.toString();
    }

    @RequestMapping("/table")
    public String table(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        mealNutritionRepository.deleteAllByDateIsNull();
        date = LocalDate.now();
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealNutritionRepository.findAllByUserAndDate(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/{dateString}")
    public String table(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable String dateString)
    {
        date = LocalDate.parse(dateString);
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealNutritionRepository.findAllByUserAndDate(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/previous")
    public String tablePrevious(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        date = date.minus(Period.ofDays(1));
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealNutritionRepository.findAllByUserAndDate(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/table/next")
    public String tableNext(@AuthenticationPrincipal CurrentUser currentUser, Model model)
    {
        date = date.plus(Period.ofDays(1));
        User user = currentUser.getUser();
        List<MealNutrition> mealNutritionsOfDay = mealNutritionRepository.findAllByUserAndDate(user, LocalDate.now());
        model.addAttribute("meals", mealNutritionsOfDay);
        model.addAttribute("date", date);
        return "/mealNutritions/your_eating";
    }

    @RequestMapping("/delete_meal/{id}")
    public String deleteMeal(@PathVariable Long id){
        MealNutrition mealNutrition = mealNutritionRepository.getById(id);
        LocalDate date = mealNutrition.getDate();
        mealNutritionRepository.delete(mealNutrition);
        return "redirect:/mealNutritions/table/" + date.toString();
    }
}
