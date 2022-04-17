package pl.coderslab.user;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.coderslab.user.User;

public interface UserService {
    User findByUserName(String name);

    User findByEmail(String email);


    User saveUser(User user);

    boolean matches(String oldPassword, String password);

    public void updatePassword(User user);

    User registerNewUserAccount(UserDto userDto);
}