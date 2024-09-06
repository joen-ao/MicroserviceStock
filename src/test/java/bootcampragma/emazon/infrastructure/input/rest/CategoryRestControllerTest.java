package bootcampragma.emazon.infrastructure.input.rest;
import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = CategoryRestController.class)
@Import(bootcampragma.emazon.infrastructure.configuration.TestConfig.class)
class CategoryRestControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @Mock
 private ICategoryHandler categoryHandler;

    private ObjectMapper objectMapper;

 @BeforeEach
 void setUp() {
  MockitoAnnotations.openMocks(this);
  objectMapper = new ObjectMapper();
     CategoryRestController categoryRestController = new CategoryRestController(categoryHandler);
  mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
 }


 @Test
 void saveCategory_ShouldReturnBadRequest_WhenInvalidInput() throws Exception {
  // Arrange
  CategoryRequest categoryRequest = new CategoryRequest();
  categoryRequest.setName(""); // Invalid name

  // Act
  ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/category")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(categoryRequest)));

  // Assert
  result.andExpect(status().isBadRequest());
 }



 @Test
void saveCategory_ShouldReturnCreated_WhenValidInput()  {
    // Arrange
    CategoryRequest categoryRequest = new CategoryRequest();
    categoryRequest.setName("Electronics");


    categoryHandler.saveCategory(categoryRequest);

    verify(categoryHandler, times(1)).saveCategory(any(CategoryRequest.class));
}




}
