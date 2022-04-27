package pl.coderslab.account.appUser.userGoals;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.account.appUser.CurrentUser;
import pl.coderslab.account.appUser.User;


import java.time.LocalDate;
import java.time.Period;

@Controller
@Slf4j
public class UserGoalsController {

    private LocalDate date;
    private final EatenByUserService eatenByUserService;
    private final UserGoalsRepository userGoalsRepository;

    public UserGoalsController(EatenByUserService eatenByUserService, UserGoalsRepository userGoalsRepository) {
        this.eatenByUserService = eatenByUserService;
        this.userGoalsRepository = userGoalsRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        date = LocalDate.now();
        EatenByUser eatenByUser = eatenByUserService.getEatenByUser(user,date);
        UserGoals userGoals = user.getUserGoals();
        model.addAttribute("eaten", eatenByUser);
        model.addAttribute("goals", userGoals);
        return "/user/dashboard";
    }

    @GetMapping("/dashboard/{page}")
    public String dashboardPrevious(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable String page) {
        date = page.equals("next") ? date.plus(Period.ofDays(1)) : date.minus(Period.ofDays(1));
        User user = currentUser.getUser();
        UserGoals userGoals = user.getUserGoals();
        EatenByUser eatenByUser = eatenByUserService.getEatenByUser(user,date);
        model.addAttribute("goals", userGoals);
        model.addAttribute("eaten", eatenByUser);
        return "/user/dashboard";
    }
    @GetMapping("/user/goals")
    public String editGoals(@AuthenticationPrincipal CurrentUser currentUser,Model model) {
        UserGoals userGoals = currentUser.getUser().getUserGoals();
        model.addAttribute("userGoals", userGoals);
        return "/user/goalsEdit";
    }

    @PostMapping("/user/goals")
    public String editGoalsPost(UserGoals userGoals) {
        userGoalsRepository.save(userGoals);
        return "redirect:/dashboard";
    }






}