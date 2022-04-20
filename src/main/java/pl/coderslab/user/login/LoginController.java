package pl.coderslab.user.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.user.User;
import pl.coderslab.user.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping(value = {"/login"})
    public String login() {
        return "/login";
    }
}