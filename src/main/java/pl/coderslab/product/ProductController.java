package pl.coderslab.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public String all(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "/product/product_all";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("product", new Product());
        return "/product/product_add";
    }

    @PostMapping("/add")
    public String addPost(@Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("product", product);
            return "/product/product_add";
        }
        productRepository.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
            productRepository.delete(productRepository.findProductById(id));
        return "redirect:/product/all";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable long id){
        model.addAttribute("product", productRepository.findProductById(id));
        return "/product/product_edit";
    }

    @PostMapping("/update")
    public String updatePost(@Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("product", product);
            return "/product/product_edit";
        }
        productRepository.save(product);
        return "redirect:/product/all";
    }




}
