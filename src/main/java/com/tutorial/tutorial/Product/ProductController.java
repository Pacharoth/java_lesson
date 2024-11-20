package com.tutorial.tutorial.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    private String pathUpload = "images/";
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public String getProducts(Model model) {
        List<ProductEntity> productEntity = productRepository.findAll();
        model.addAttribute("titleHeader", "List Product");
        model.addAttribute("products", productEntity);
        model.addAttribute("content", "fragments/list_product");
        return "products";
    }

    @GetMapping("/create-form")
    public String getProductForm(Model model) throws IOException {
        model.addAttribute("productForm", new ProductDTO());
        model.addAttribute("titleHeader", "Create Product");
        model.addAttribute("content", "fragments/form");
        return "products";
    }

    @PostMapping("/save-product")
    public String saveProduct(@Valid @ModelAttribute("productForm") ProductDTO productForm, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            if(productForm.getImage().isEmpty()){
                bindingResult.rejectValue("image", "error.productForm", "Image is required.");
            }
            model.addAttribute("titleHeader", "Create Product");
            model.addAttribute("content", "fragments/form");
            return "products";
        }
      
        MultipartFile image = productForm.getImage();
        String imageUrl = "";
        Path pathImage = Paths.get(pathUpload + image.getOriginalFilename());
        Files.createDirectories(pathImage.getParent());
        Files.write(pathImage, image.getBytes());
        imageUrl = "/files/" + image.getOriginalFilename();
        productRepository.save(new ProductEntity(productForm.getName(), productForm.getPrice(), imageUrl));

        return "redirect:/";
    }

    @GetMapping("/files/{file:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("file") String filename) {
        try {
            Path imageFile = Paths.get(pathUpload).resolve(filename);
            Resource resource = new UrlResource(imageFile.toUri());
            if (resource.exists() || resource.isReadable())
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
        } catch (Exception e) {
        }
        return ResponseEntity.badRequest().build();
    }

}
