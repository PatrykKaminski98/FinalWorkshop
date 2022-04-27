package pl.coderslab.account.appUser;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.account.appUser.userGoals.UserGoals;
import pl.coderslab.account.appUser.userGoals.UserGoalsRepository;
import pl.coderslab.account.registration.token.ConfirmationToken;
import pl.coderslab.account.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    private final UserGoalsRepository userGoalsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));
        }
        return new CurrentUser(user.getUsername(), user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked(), user.getAuthorities(), user);
    }

    public String signUpUser(User user){
        boolean emailExists = userRepository.findByEmail(user.getEmail())
                .isPresent();
        boolean userExists = userRepository.findByUsername(user.getUsername()) != null;

        if(emailExists){
            throw new IllegalArgumentException("email already exists");
        }
        if(userExists){
            throw new IllegalArgumentException("user already exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        UserGoals userGoals = new UserGoals();
        userGoalsRepository.save(userGoals);

        user.setUserGoals(userGoals);
        userRepository.save(user); //registration complete

        // here start of confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);


        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }


    public boolean matches(String rawPassword, String oldPassword){
        return bCryptPasswordEncoder.matches(rawPassword, oldPassword);
    }
}