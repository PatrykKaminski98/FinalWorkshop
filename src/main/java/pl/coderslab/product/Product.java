package pl.coderslab.product;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.ingredient.Ingredient;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    private String name;
    @NotBlank(message = "Nazwa producenta nie może być pusta")
    private String producent;
    private float kilocalories;
    private float proteins;
    private float carbohydrates;
    private float fats;
}
