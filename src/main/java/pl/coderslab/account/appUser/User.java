package pl.coderslab.account.appUser;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.coderslab.account.appUser.userGoals.UserGoals;
import pl.coderslab.validators.ValidEmail;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @ValidEmail
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private boolean locked = false;
    private boolean enabled = false;

    @OneToOne
    private UserGoals userGoals;

    public User(String username,
                   String email,
                   String password,
                   UserRole UserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = UserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
