package pl.coderslab.user.user_goals;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int kilocalories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @OneToOne
     private User user;
}
