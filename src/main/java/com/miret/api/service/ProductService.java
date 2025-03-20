
package com.miret.api.service;

import com.miret.api.model.Product;
import com.miret.api.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    public List<Product>listProducts() {
        return repository.findAll();
    }
    
    public void saveProduct(Product product) {
        repository.save(product);
    }
    
    public Product getProductById(Integer id) {
        return repository.findById(id).get();
    }
    
    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
