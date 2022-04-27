package pl.coderslab.meal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotBlank(message = "Posiłek musi mieć nazwe")
    private String name;

    @ManyToMany
    @NotEmpty(message = "Posiłek musi zawierać minimum jeden produkt")
    private List<Product> products;
}
