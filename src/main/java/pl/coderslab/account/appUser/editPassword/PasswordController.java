package pl.coderslab.account.appUser.editPassword;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.account.appUser.CurrentUser;
import pl.coderslab.account.appUser.User;
import pl.coderslab.account.appUser.UserRepository;
import pl.coderslab.account.appUser.UserService;

import javax.validation.Valid;


@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/password")
public class PasswordController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute
    NewPasswordRequest passwordsRequest(){
        return new NewPasswordRequest();
    }

    @GetMapping("/edit")
    public String editPassword() {
        return "/user/editPassword";
    }

    @PostMapping("/edit")
    public String editPasswordPost(@AuthenticationPrincipal CurrentUser customUser, @Valid NewPasswordRequest request, BindingResult result) {
        User currentUser = customUser.getUser();
        if(!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())){
            FieldError fieldError = new FieldError("request", "password", "Podano nieprawidłowe hasło");
            result.addError(fieldError);
        }

        if(result.hasErrors()){
            return "/user/editPassword";
        }

        currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(currentUser);
        return "redirect:/dashboard";
    }
}