package pl.coderslab.meal;

import lombok.*;
import pl.coderslab.product.Product;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    private List<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
