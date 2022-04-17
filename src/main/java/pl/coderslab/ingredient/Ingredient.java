package pl.coderslab.ingredient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.history.History;
import pl.coderslab.meal.Meal;
import pl.coderslab.meal.MealNutrition;
import pl.coderslab.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
public class Ingredient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(columnDefinition = "float default 0")
    private float kilocalories;
    @Column(columnDefinition = "float default 0")
    private float proteins;
    @Column(columnDefinition = "float default 0")
    private float carbohydrates;
    @Column(columnDefinition = "float default 0")
    private float fats;
    @OneToOne
    @NotNull
    private Product product;
    @Min(1)
    private int productQuantity;


    @PrePersist
    @PreUpdate
    private void calcNutritions(){
        kilocalories = product.getKilocalories() * productQuantity/100;
        proteins = product.getProteins() * productQuantity/100;
        carbohydrates = product.getCarbohydrates() * productQuantity/100;
        fats =product.getFats() * productQuantity/100;
    }
}
