package pl.coderslab.meal;

import lombok.Data;
import pl.coderslab.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<Product> products;
}
