package pl.coderslab.user.user_goals;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.user.CurrentUser;
import pl.coderslab.user.User;
import pl.coderslab.user.UserService;
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
public class UserGoalsController {

    private LocalDate date;

    private final UserService userService;
    private final EatenByUserService eatenByUserService;

    private final UserGoalsService userGoalsService;

    public UserGoalsController(UserService userService, EatenByUserService userGoalsService, UserGoalsService userGoalsService1) {
        this.userService = userService;
        this.eatenByUserService = userGoalsService;
        this.userGoalsService = userGoalsService1;
    }
    @GetMapping("/user/goals")
    public String editGoals(Model model, HttpSession session) {
        User currentUser = (User)session.getAttribute("user");
        UserGoals userGoals = userGoalsService.findUserGoalsByUser(currentUser);
        if(userGoals == null) {
            userGoals = new UserGoals();
            userGoals.setUser(currentUser);
        }
        model.addAttribute("userGoals", userGoals);
        return "/user/goalsEdit";
    }

    @PostMapping("/user/goals")
    public String editGoalsPost(UserGoals userGoals, HttpSession session) {
        userGoalsService.save(userGoals);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model, HttpSession session) {
        User currentUser;
        if(session.getAttribute("user") != null){
            currentUser = (User) session.getAttribute("user");
        } else{
            currentUser = customUser.getUser();
            session.setAttribute("user", currentUser);
        }
        date = LocalDate.now();
        EatenByUser eatenByUser = eatenByUserService.getEatenByUser(currentUser,date);
        UserGoals userGoals = userGoalsService.findUserGoalsByUser(currentUser);
        model.addAttribute("eaten", eatenByUser);
        model.addAttribute("goals", userGoals);
        return "/user/dashboard";
    }

    @GetMapping("/dashboard/{page}")
    public String dashboardPrevious(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable String page) {
        if(page.equals("next")){
            date = date.plus(Period.ofDays(1));
        } else {
            date = date.minus(Period.ofDays(1));
        }
        User currentUser = customUser.getUser();
        UserGoals userGoals = userGoalsService.findUserGoalsByUser(currentUser);
        EatenByUser eatenByUser = eatenByUserService.getEatenByUser(currentUser,date);
        model.addAttribute("goals", userGoals);
        model.addAttribute("eaten", eatenByUser);
        return "/user/dashboard";
    }





}