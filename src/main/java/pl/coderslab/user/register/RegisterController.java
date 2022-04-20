package pl.coderslab.user.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.errors.UserAlreadyExistException;
import pl.coderslab.user.User;
import pl.coderslab.user.UserDto;
import pl.coderslab.user.UserService;
import pl.coderslab.user.user_goals.UserGoalsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    private final UserGoalsService userGoalsService;

    public RegisterController(UserService userService, UserGoalsService userGoalsService) {
        this.userService = userService;
        this.userGoalsService = userGoalsService;
    }

    @GetMapping("/form")
    public String registerForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "/register";
    }

    @PostMapping("/form")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "/register";
        }
        try {
            User registered = userService.registerNewUserAccount(userDto);
            userGoalsService.firstSave(registered);

        } catch (UserAlreadyExistException uaeEx) {
            result.addError(new FieldError("user","email", "Taki email już istnieje"));
        }
        if(result.hasErrors()){
            return "/register";
        }

        model.addAttribute("registerSuccess", true);
        return "redirect:/login";
    }
}
