package pl.coderslab.user.user_goals;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EatenByUser {
    private int kilocalories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    private LocalDate date;
}
