package pl.coderslab.meal;

import lombok.Data;
import pl.coderslab.history.History;
import pl.coderslab.ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MealNutrition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String mealName;
    private int kilocalories;
    private int proteins;
    private int carbohydrates;
    private int fats;
    @OneToOne
    private History history;
    @OneToMany
    private List<Ingredient> ingredients;
}
