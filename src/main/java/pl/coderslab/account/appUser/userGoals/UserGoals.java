package pl.coderslab.account.appUser.userGoals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.account.appUser.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class UserGoals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int kilocalories;
    private int proteins;
    private int carbohydrates;
    private int fats;

    @PrePersist
    private void setDefault(){
        this.kilocalories = 2600;
        this.proteins = 180;
        this.carbohydrates = 340;
        this.fats = 90;
    }
}
