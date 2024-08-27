package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

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
}
