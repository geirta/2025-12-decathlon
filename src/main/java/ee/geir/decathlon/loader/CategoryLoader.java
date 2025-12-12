package ee.geir.decathlon.loader;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public CategoryLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        List<String> categoryNames = List.of(
                "100M_SPRINT","LONG_JUMP","SHOT_PUT","HIGH_JUMP","400_METRES",
                "110M_HURDLES","DISCUS","POLE_VAULT","JAVELIN","1500_METRES"
        );

        for (String name : categoryNames) {
            if (categoryRepository.findByName(name) == null) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            }
        }
    }

}
