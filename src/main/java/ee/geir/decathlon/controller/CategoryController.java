package ee.geir.decathlon.controller;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.repository.CategoryRepository;
import ee.geir.decathlon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("category")
    public List<Category> addCategory(@RequestBody Category category) {
        categoryService.validate(category);
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    @GetMapping("category/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).get();
    }

    @PostMapping("categories")
    public List<Category> addCategories(@RequestBody List<Category> categories) {
        categoryService.validate(categories);
        categoryRepository.saveAll(categories);
        return categoryRepository.findAll();
    }

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
