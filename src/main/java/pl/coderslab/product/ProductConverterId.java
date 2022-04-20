package pl.coderslab.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ProductConverterId implements Converter<Long, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product convert(Long source) {
        return productRepository.findProductById(source);
    }
}
