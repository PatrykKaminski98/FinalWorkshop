package pl.coderslab.user.editPassword;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.user.CurrentUser;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;

@Controller
@Slf4j
@RequestMapping("/password")
public class PasswordController {

    private final UserService userService;

    public PasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String editPassword() {
        return "/admin/editPassword";
    }

    @PostMapping("/edit")
    public String editPasswordPost(@AuthenticationPrincipal CurrentUser customUser, @RequestParam String oldPassword, @RequestParam String newPassword1, @RequestParam String newPassword2) {
        User currentUser = customUser.getUser();
        if(userService.matches(oldPassword, currentUser.getPassword())){
            if(newPassword1.equals(newPassword2)){
                currentUser.setPassword(newPassword1);
                userService.updatePassword(currentUser);
            } else{
                log.info("hasła nie są takie same");
            }
        }
        else{
            log.info("podano niepoprawne hasło");
        }

        return "redirect:/dashboard";
    }
}
