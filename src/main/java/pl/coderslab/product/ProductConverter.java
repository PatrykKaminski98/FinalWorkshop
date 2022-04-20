package pl.coderslab.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.product.Product;
import pl.coderslab.product.ProductRepository;

public class ProductConverter implements Converter<String, Product> {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product convert(String source) {
        return productRepository.findByName(source);
    }
}
