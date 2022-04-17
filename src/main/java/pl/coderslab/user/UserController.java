package pl.coderslab.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.user.user_goals.EatenByUser;
import pl.coderslab.user.user_goals.EatenByUserService;
import pl.coderslab.user.user_goals.UserGoals;
import pl.coderslab.user.user_goals.UserGoalsService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;

@Controller
@Slf4j
@SessionAttributes("user")
public class UserController {

    private final UserService userService;
    private final EatenByUserService eatenByUserService;

    private final UserGoalsService userGoalsService;

    public UserController(UserService userService, EatenByUserService userGoalsService, UserGoalsService userGoalsService1) {
        this.userService = userService;
        this.eatenByUserService = userGoalsService;
        this.userGoalsService = userGoalsService1;
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("qwerty@wp.pl");
        userService.saveUser(user);
        return "admin";
    }
}