package bootcampragma.emazon.infrastructure.exceptionHandler;

import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;
import bootcampragma.emazon.domain.exception.category.CategoryAlreadyExistException;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import bootcampragma.emazon.infrastructure.exceptionhandler.GlobalControllerAdvisor;
import bootcampragma.emazon.infrastructure.input.rest.CategoryRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerAdvisorTest {

    @InjectMocks
    private GlobalControllerAdvisor globalControllerAdvisor;

    CategoryRestController categoryRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ICategoryHandler categoryHandler = mock(ICategoryHandler.class);
        categoryRestController = new CategoryRestController(categoryHandler);
    }

    @Test
    void handleBrandAlreadyExistException_ShouldReturnBadRequest() {
        // Arrange
        BrandAlreadyExistException ex = new BrandAlreadyExistException();
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("error", "Brand already exist");

        // Act
        ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleBrandAlreadyExistException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

   @Test
void handleCategoryAlreadyExistsException_ShouldReturnBadRequest() {
    // Arrange
    CategoryAlreadyExistException ex = new CategoryAlreadyExistException();
    Map<String, String> expectedResponse = new HashMap<>();
    expectedResponse.put("error", ex.getMessage());

    // Act
    ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleCategoryAlreadyExistsException(ex);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(expectedResponse, response.getBody());
}

    @Test
    void handleInvalidSortDirectionException_ShouldReturnBadRequestAndErrorMessage() {
        // Arrange
        String errorMessage = "Invalid sort direction";
        InvalidSortDirectionException exception = new InvalidSortDirectionException(errorMessage);

        // Act
        ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleInvalidSortDirectionException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Verifica que el cuerpo de la respuesta no sea nulo antes de realizar más validaciones
        assertNotNull(response.getBody(), "El cuerpo de la respuesta no debería ser nulo");

        assertEquals(1, response.getBody().size());
        assertEquals(errorMessage, response.getBody().get("error"));
    }


    @Test
    void handleValidationExceptions_ShouldReturnValidationErrors() throws NoSuchMethodException {
        // Arrange
        BindException bindException = new BindException(new Object(), "target");
        bindException.addError(new FieldError("target", "name", "Name is required"));
        bindException.addError(new FieldError("target", "description", "Description is required"));

        BindingResult bindingResult = bindException.getBindingResult();

        // Crear un MethodParameter usando un metodo ficticio
        Method method = this.getClass().getDeclaredMethod("dummyMethod");
        MethodParameter methodParameter = new MethodParameter(method, -1);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("name", "Name is required");
        expectedErrors.put("description", "Description is required");

        // Act
        ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleValidationExceptions(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

    // Método ficticio requerido para crear un MethodParameter válido
     void dummyMethod() {
        // Este método es un marcador de posición y no necesita lógica
    }

    @Test
void handleDuplicateCategoriesException_ShouldReturnBadRequest() {
    // Arrange
    DuplicateCategoriesException ex = new DuplicateCategoriesException();
    Map<String, String> expectedResponse = new HashMap<>();
    expectedResponse.put("error", "Duplicate categories are not allowed");

    // Act
    ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleDuplicateCategoriesException(ex);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(expectedResponse, response.getBody());
}

@Test
void handleCategoriesSizeException_ShouldReturnBadRequest() {
    // Arrange
    CategoriesSizeException ex = new CategoriesSizeException();
    Map<String, String> expectedResponse = new HashMap<>();
    expectedResponse.put("error", "Categories size must be between 1 and 3");

    // Act
    ResponseEntity<Map<String, String>> response = globalControllerAdvisor.handleCategoriesSizeException(ex);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(expectedResponse, response.getBody());
}
}
