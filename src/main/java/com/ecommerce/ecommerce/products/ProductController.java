package com.ecommerce.ecommerce.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAProduct() {
        return new ResponseEntity<List<Product>>(productService.getAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id,
            @RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(id, productRequest);
        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseDelete> deleteProduct(@PathVariable("id") Integer id){
        if(productService.deleteProduct(id)){
            return new ResponseEntity<ResponseDelete>(new ResponseDelete("Delete Product Succesful"),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<ResponseDelete>(new ResponseDelete("Delete Fail"),HttpStatus.EXPECTATION_FAILED);
    }
}
