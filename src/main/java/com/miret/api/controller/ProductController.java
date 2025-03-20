package com.miret.api.controller;

import com.miret.api.model.Product;
import com.miret.api.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /product
    @GetMapping
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    // GET /producto/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /product
    @PostMapping
    public ResponseEntity<Product> newProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // PUT /product/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
        try {
            Product existingProduct = productService.getProductById(id);
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productService.saveProduct(existingProduct);
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /producto/{id}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
