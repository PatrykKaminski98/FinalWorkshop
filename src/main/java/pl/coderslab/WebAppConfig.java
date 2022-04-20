package pl.coderslab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.meal.Meal;
import pl.coderslab.meal.MealConverter;
import pl.coderslab.product.ProductConverter;
import pl.coderslab.product.ProductConverterId;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
    }



    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(getProductConverter());
        registry.addConverter(getMealConverter());
    }

    @Bean
    public ProductConverter getProductConverter() {
        return new ProductConverter();
    }

    @Bean
    public MealConverter getMealConverter() {
        return new MealConverter();
    }



}

