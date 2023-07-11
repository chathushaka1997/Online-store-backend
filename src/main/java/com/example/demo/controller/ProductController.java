package com.example.demo.controller;

import com.example.demo.exceptions.ProductNotFound;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllUsers() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getUserById(@PathVariable Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Product not found "+id));
    }

    @PostMapping
    public Product createUser(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }
}
