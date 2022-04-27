package pl.coderslab.account.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("register")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @ModelAttribute
    UserDto userDto(){
        return new UserDto();
    }

    @GetMapping("/form")
    public String registerForm(){
        return "/register";
    }

    @PostMapping("form")
    public String register(@Valid UserDto userDto, BindingResult result){
        try{
            registrationService.register(userDto);
        } catch(IllegalArgumentException e){
            FieldError fieldError = new FieldError("userDto", "email", e.getMessage());
            result.addError(fieldError);
        }

        if(result.hasErrors()){
            return "/register";
        }

        return "afterRegistration";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
