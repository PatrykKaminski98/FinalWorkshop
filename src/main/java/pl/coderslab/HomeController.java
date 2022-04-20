package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.meal.Meal_Service;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }

}




