package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldCallSaveCategoryOnPersistencePort() {
        // Arrange
        Category category = new Category(1L, "Category Name", "Category Description");

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test  // Agregamos la anotación @Test aquí
    void testGetAllCategory() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortDirection = "asc";

        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");

        List<Category> expectedCategories = Arrays.asList(category1, category2);

        when(categoryPersistencePort.getAllCategory(page, size, sortDirection)).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = categoryUseCase.getAllCategory(page, size, sortDirection);

        // Assert
        assertEquals(expectedCategories, actualCategories);
        verify(categoryPersistencePort, times(1)).getAllCategory(page, size, sortDirection);
    }
}
