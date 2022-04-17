package pl.coderslab.user.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.user.User;
import pl.coderslab.user.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpSession session) {
        return "/login";
    }
}