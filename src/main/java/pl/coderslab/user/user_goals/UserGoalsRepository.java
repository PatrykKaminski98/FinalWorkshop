package pl.coderslab.user.user_goals;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.user.User;

public interface UserGoalsRepository extends JpaRepository<UserGoals, Long> {

    UserGoals findUserGoalsByUser(User user);
}
