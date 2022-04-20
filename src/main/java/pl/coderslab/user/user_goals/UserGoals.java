package pl.coderslab.user.user_goals;

import lombok.Builder;
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

    @PrePersist
    private void setDefault(){
        this.kilocalories = 2600;
        this.proteins = 180;
        this.carbohydrates = 340;
        this.fats = 90;
    }
}
