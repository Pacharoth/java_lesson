package com.springa.springa.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    public String getProducts(Model model) {
        List<ProductEntity> products= productRepository.findAll();
        model.addAttribute("newProduct", new ProductEntity());
        model.addAttribute("products", products);
        return "products/product";
    }
    @PostMapping("/saveProduct")
    public String postProduct(@ModelAttribute("newProduct") ProductEntity productEntity) {
        productRepository.save(productEntity);
        return "redirect:/products";
    }
    
    
}
