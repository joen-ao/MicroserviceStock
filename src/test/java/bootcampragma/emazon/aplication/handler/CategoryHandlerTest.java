package bootcampragma.emazon.aplication.handler;


import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.aplication.mapper.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

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
    @Test
    void getAllCategory_ShouldReturnMappedResponseList() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String sortDirection = "asc";

        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");

        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponse response1 = new CategoryResponse();
        CategoryResponse response2 = new CategoryResponse();

        List<CategoryResponse> expectedResponses = Arrays.asList(response1, response2);

        when(categoryServicePort.getAllCategory(page, size, sortDirection)).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(expectedResponses);

        // Act
        List<CategoryResponse> actualResponses = categoryHandler.getAllCategory(page, size, sortDirection);

        // Assert
        assertEquals(expectedResponses, actualResponses);
        verify(categoryServicePort, times(1)).getAllCategory(page, size, sortDirection);
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }
}