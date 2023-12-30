package com.ecommerce.ecommerce.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product(productRequest.getName(), productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getExpireDate());
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id,ProductRequest productRequest){
        // select * from products where id=1;
        Optional<Product> searchingProduct = productRepository.findById(id);
        Product foundedProduct = searchingProduct.get();
        foundedProduct.updateProduct(id, 
        productRequest.getName(),
        productRequest.getDescription(),
        productRequest.getPrice(),
        productRequest.getExpireDate());
        return  productRepository.save(foundedProduct);
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public boolean deleteProduct(Integer id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}