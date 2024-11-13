package com.springc.springgroupc.Product;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("products")
public class ProductController {
    private final String locationUpload = "images/";
    private final String routeUI="products/includes";
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/form-create-support-image")
    public String getMethodName(Model model) {
        model.addAttribute("content",routeUI+"/form-request" );
        model.addAttribute("title", "Form Create With Image");
        return "products/layout";
    }
    
    @GetMapping("/form-create")
    public String getForm(Model model) {
        model.addAttribute("formProduct", new ProductEntity());
        model.addAttribute("content", routeUI+"/form");
        model.addAttribute("title", "Form Create");
        return "products/layout";
    }

    @GetMapping("")
    public String getProducts(Model model) {
        List<ProductEntity> productEntities = productRepository.findAll();
        model.addAttribute("content", routeUI+"/list-form");
        model.addAttribute("products", productEntities);
        model.addAttribute("title", "Welcome to Product Page");
        return "products/layout";
    }

    @PostMapping("/save")
    public String postProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            @RequestParam("country") String country, Model model) {
        String imageUrl = "";
        if (!image.isEmpty()) {
            try {
                Path imagePath = Paths.get(locationUpload + image.getOriginalFilename());
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                imageUrl = "products/files/" + image.getOriginalFilename();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        productRepository.save(new ProductEntity(name, country,  code, price, imageUrl, description));
        model.addAttribute("message", "success");
        return "redirect:/products";
    }

    @PostMapping("/saveProduct")
    public String postProduct2(@ModelAttribute("formProduct") ProductEntity prodiucEntity) {
        productRepository.save(prodiucEntity);

        return "redirect:/products";
    }

    // products/files/image.png
    @GetMapping("/files/{file:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("file") String filename) {
        Path file = Paths.get(locationUpload).resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.isReadable() || resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        } catch (Exception e) {
        }
        return ResponseEntity.notFound().build();
    }

}
