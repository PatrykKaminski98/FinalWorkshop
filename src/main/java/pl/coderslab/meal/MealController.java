package pl.coderslab.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/meal")
public class MealController {
    private final Meal_Service meal_service;
    private final ProductService productService;

    public MealController(Meal_Service meal_service, ProductService productService) {
        this.meal_service = meal_service;
        this.productService = productService;
    }

    @ModelAttribute("meals")
    public List<Meal> meals(){return meal_service.findAll();}

    @ModelAttribute("products")
    public List<Product> products(){
        return productService.getAllProducts();
    }

    @RequestMapping("/table")
    public String table(){
        return "/meals/meal_table";
    }

    @RequestMapping("/details/{mealId}")
    public String details(Model model, @PathVariable long mealId){
        model.addAttribute("meal", meal_service.findById(mealId));
        return "/meals/details";
    }

    @GetMapping("/add")
    public String addMeal(Model model){
        model.addAttribute("meal", new Meal());
        return "/meals/addMeal";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addMealPost(Meal meal){
        return meal.toString();
    }

    @RequestMapping("/deleteProduct/{mealId}/{productId}")
    public String deleteProduct(@PathVariable long mealId, @PathVariable long productId){
        meal_service.deleteProduct(mealId, productId);
        return "redirect:/meal/details/" + mealId;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        meal_service.deleteById(id);
        return "redirect:/meal/table";
    }

    @RequestMapping("/addProduct/{mealId}")
    public String addProduct(Model model, @PathVariable long mealId){
        model.addAttribute("mealId", mealId);
        return "/meals/addProduct";
    }

    @RequestMapping("/productAdd/{mealId}/{productId}")
    public String addProduct2(@PathVariable long mealId, @PathVariable long productId){
        meal_service.addProduct(mealId, productId);
        return "redirect:/meal/details/" + mealId;
    }

    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable long id, Model model){
        model.addAttribute("meal", meal_service.findById(id));
        model.addAttribute("product", new Product());
        return "/meals/edit_meal";
    }

    @PostMapping("/update")
        public String updatePost(Model model, Product product, @RequestParam long mealId){
        System.out.println(mealId);
        Meal meal = meal_service.findById(mealId);
        product = productService.getById(product.getId());
        List<Product> products = meal.getProducts();
        products.add(product);
        System.out.println("działa");
        meal.setProducts(products);
        return "redirect:/meal/update/{" + meal.getId() + "}";
    }



}
