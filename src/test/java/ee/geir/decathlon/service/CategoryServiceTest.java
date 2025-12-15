package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    // TEST: CAN'T ADD A CATEGORY IF IT ALREADY EXISTS
    @Test
    void givenCategoryAlreadyExists_whenAddingExistingCategory_thenExceptionIsThrown() {
        Category category = new Category();
        category.setName("DISCUS");
        when(categoryRepository.existsByNameIgnoreCase("DISCUS")).thenReturn(true);

        Category newCat = new Category();
        newCat.setName("DISCUS");
        String msg = assertThrows(RuntimeException.class, () -> categoryService.validate(newCat)).getMessage();
        assertEquals("Category named '" + newCat.getName() + "' already exists", msg);
    }
}