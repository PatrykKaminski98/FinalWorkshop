package pl.coderslab.history;

import lombok.Data;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.user.User;
import pl.coderslab.meal.Meal;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDate date;

}
