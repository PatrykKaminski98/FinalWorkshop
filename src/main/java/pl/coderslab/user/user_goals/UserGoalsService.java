package pl.coderslab.user.user_goals;

import org.springframework.stereotype.Service;
import pl.coderslab.user.User;

@Service
public class UserGoalsService {
    private final UserGoalsRepository userGoalsRepository;

    public UserGoalsService(UserGoalsRepository userGoalsRepository) {
        this.userGoalsRepository = userGoalsRepository;
    }

    public UserGoals findUserGoalsByUser(User user){
        return userGoalsRepository.findUserGoalsByUser(user);
    }

    public void save(UserGoals userGoals){
        userGoalsRepository.save(userGoals);
    }

    public void firstSave(User user){
        UserGoals userGoals = new UserGoals();
        userGoals.setUser(user);
        save(userGoals);}
}
