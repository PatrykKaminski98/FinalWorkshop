package pl.coderslab.product;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    private String name;
    private float kilocalories;
    private float proteins;
    private float carbohydrates;
    private float fats;
}
