package pl.coderslab.meal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/meal")
public class MealController {
    private final MealService mealService;
    private MealRepository mealRepository;

    private ProductRepository productRepository;

    @ModelAttribute("meals")
    public List<Meal> meals(){return mealRepository.findAll();}

    @ModelAttribute("products")
    public List<Product> products(){
        return productRepository.findAll();
    }

    @RequestMapping("/table")
    public String table(){
        return "/meals/meal_table";
    }

    @RequestMapping("/details/{mealId}")
    public String details(Model model, @PathVariable long mealId){
        model.addAttribute("meal", mealRepository.findMealById(mealId));
        return "/meals/details";
    }
    @GetMapping("/add")
    public String addMealGet(Model model){
        model.addAttribute("meal", new Meal());
        return "meals/addMeal";
    }

    @PostMapping("/add")
    public String addMealPost(@Valid Meal meal, BindingResult result){
        if(result.hasErrors()){
            return "meals/addMeal";
        }
        mealRepository.save(meal);
        return "redirect:/meal/table";
    }

    @RequestMapping("/deleteProduct/{mealId}/{productId}")
    public String deleteProduct(@PathVariable long mealId, @PathVariable long productId){
        mealService.deleteProduct(mealId, productId);
        return "redirect:/meal/details/" + mealId;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        mealRepository.deleteById(id);
        return "redirect:/meal/table";
    }

    @RequestMapping("/addProduct/{mealId}")
    public String addProduct(Model model, @PathVariable long mealId){
        model.addAttribute("mealId", mealId);
        return "/meals/addProduct";
    }

    @RequestMapping("/productAdd/{mealId}/{productId}")
    public String addProduct2(@PathVariable long mealId, @PathVariable long productId){
        mealService.addProduct(mealId, productId);
        return "redirect:/meal/details/" + mealId;
    }



}
