package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.mapper.CategoryRequestMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldCallSaveCategoryOnServicePort() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Category Name");
        categoryRequest.setDescription("Category Description");

        Category category = new Category(1L, "Category Name", "Category Description");

        // Mock the mapping
        when(categoryRequestMapper.toRequest(categoryRequest)).thenReturn(category);

        // Act
        categoryHandler.saveCategory(categoryRequest);

        // Assert
        verify(categoryRequestMapper, times(1)).toRequest(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }
}
