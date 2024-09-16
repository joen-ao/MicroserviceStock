package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.exception.category.CategoryAlreadyExistException;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        // Arrange
        Category category = new Category();
        category.setName("Existing Category");
        when(categoryRepository.existsByName(category.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(CategoryAlreadyExistException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, never()).saveCategory(category);
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category();
        category.setName("New Category");
        when(categoryRepository.existsByName(category.getName())).thenReturn(false);

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void getAllCategory_ShouldReturnPage_WhenCalled() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        long totalElements = 100;  // Ajusta este valor según el total de elementos que esperas
        int totalPages = 10;       // Ajusta este valor según el total de páginas que esperas
        CustomPage<Category> expectedPage = new CustomPage<>();

        when(categoryPersistencePort.getAllCategory(0, 10, "ASC")).thenReturn(expectedPage);

        // Act
        CustomPage<Category> resultPage = categoryUseCase.getAllCategory(0, 10, "ASC");

        // Assert
        assertEquals(expectedPage, resultPage);
    }
}
