package pl.coderslab.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ProductConverterID implements Converter<Long, Product> {


    @Autowired
    private ProductService productService;

    @Override
    public Product convert(Long source) {
        return productService.getById(source);
    }
}
