package pl.coderslab.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> getAllProductsNames(){
        List<Product> all = productRepository.findAll();
        return all.stream()
                .map(product -> product.getName())
                .collect(Collectors.toList());
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getById(long id){
        return productRepository.findProductById(id);
    }
}
