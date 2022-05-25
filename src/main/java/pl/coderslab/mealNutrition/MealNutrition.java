package pl.coderslab.mealNutrition;

import lombok.Data;
import pl.coderslab.account.appUser.User;
import pl.coderslab.ingredient.Ingredient;
import pl.coderslab.meal.Meal;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class MealNutrition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @OneToOne
    private Meal meal;
    private int kilocalories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }


}
