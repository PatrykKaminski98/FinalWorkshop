package pl.coderslab.account.appUser.userGoals;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.account.appUser.User;

public interface UserGoalsRepository extends JpaRepository<UserGoals, Long> {

}
