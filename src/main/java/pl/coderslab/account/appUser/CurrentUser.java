package pl.coderslab.account.appUser;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final pl.coderslab.account.appUser.User user;
    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.account.appUser.User user) {
        super(username, password, enabled, accountNonExpired,credentialsNonExpired, accountNonLocked,authorities);
        this.user = user;
    }
    public pl.coderslab.account.appUser.User getUser() {return user;}
}