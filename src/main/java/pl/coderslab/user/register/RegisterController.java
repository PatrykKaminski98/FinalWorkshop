package pl.coderslab.user.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.errors.UserAlreadyExistException;
import pl.coderslab.user.User;
import pl.coderslab.user.UserDto;
import pl.coderslab.user.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
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
        } catch (UserAlreadyExistException uaeEx) {
            result.addError(new FieldError("user","email", "Taki email już istnieje"));
        }
        if(result.hasErrors()){
            return "/register";
        }

        return "redirect:/login";
    }
}
