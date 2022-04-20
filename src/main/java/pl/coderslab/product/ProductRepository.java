package pl.coderslab.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(long id);

    Product findByName(String name);





}
