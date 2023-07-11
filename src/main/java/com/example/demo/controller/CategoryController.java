package com.example.demo.controller;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllUsers() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getUserById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public Category createUser(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
