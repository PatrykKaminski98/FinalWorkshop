package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.meal.Meal_Service;

@Controller
public class HomeController {

    @RequestMapping("/home")
    @ResponseBody
    public String home(){
        return "home";
    }

}




