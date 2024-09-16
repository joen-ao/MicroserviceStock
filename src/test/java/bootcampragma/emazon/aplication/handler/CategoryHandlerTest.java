package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.mapper.request.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.response.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void getAllCategory_ShouldThrowException_WhenPageIsNegative() {
        // Arrange
        Integer page = -1;
        Integer size = 10;
        String sortDirection = "ASC";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> categoryHandler.getAllCategory(page, size, sortDirection));
    }


}
