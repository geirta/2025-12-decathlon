package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void validate(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new RuntimeException("Category named '" + category.getName() + "' already exists");
        }
    }

    public void validate(List<Category> categories) {
        for (Category category : categories) {
            if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
                throw new RuntimeException("Category named '" + category.getName() + "' already exists");
            }
        }
    }

}