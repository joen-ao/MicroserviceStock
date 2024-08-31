package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.aplication.mapper.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
    void saveCategory_ShouldReturnCategoryResponse_WhenCategoryRequestIsValid() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Electronic items");

        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronic items");

        when(categoryRequestMapper.toRequest(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);
        when(categoryResponseMapper.toResponse(category)).thenReturn(new CategoryResponse());

        CategoryResponse categoryResponse = categoryHandler.saveCategory(categoryRequest);

        assertEquals("Electronics", categoryResponse.getName());
        assertEquals("Electronic items", categoryResponse.getDescription());
    }

    @Test
    void getAllCategory_ShouldThrowException_WhenPageIsNegative() {
        // Arrange
        Integer page = -1;
        Integer size = 10;
        String sortDirection = "ASC";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> categoryHandler.getAllCategory(page, size, sortDirection));
    }

    @Test
    void getAllCategory_ShouldReturnPage_WhenCalled() {
        // Arrange
        Category category = new Category();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        CustomPage<Category> categoryPage = new CustomPage<>(categoryList, 0, 10, 1, 1);

        CategoryResponse categoryResponse = new CategoryResponse();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryResponseList.add(categoryResponse);

        when(categoryServicePort.getAllCategory(anyInt(), anyInt(), any())).thenReturn(categoryPage);
        when(categoryResponseMapper.toResponseList(categoryPage.getContent())).thenReturn(categoryResponseList);

        // Act
        CustomPage<CategoryResponse> resultPage = categoryHandler.getAllCategory(0, 10, "ASC");

        // Assert
        assertEquals(categoryResponseList, resultPage.getContent());
        assertEquals(categoryPage.getPageNumber(), resultPage.getPageNumber());
        assertEquals(categoryPage.getPageSize(), resultPage.getPageSize());
        assertEquals(categoryPage.getTotalElements(), resultPage.getTotalElements());
        assertEquals(categoryPage.getTotalPages(), resultPage.getTotalPages());
    }
}
