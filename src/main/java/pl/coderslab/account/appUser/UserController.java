package pl.coderslab.account.appUser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@SessionAttributes("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("qwerty@wp.pl");
        user.setEnabled(true);
        user.setUserRole(UserRole.ROLE_ADMIN);
        userService.signUpUser(user);
        return "admin";
    }
}