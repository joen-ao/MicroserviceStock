package bootcampragma.emazon.infrastructure.exceptionHandler;

import bootcampragma.emazon.infrastructure.exception.*;
import bootcampragma.emazon.infrastructure.exceptionhandler.CategoryControllerAdvisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryControllerAdvisorTest {

    private CategoryControllerAdvisor categoryControllerAdvisor;

    @BeforeEach
    void setUp() {
        categoryControllerAdvisor = new CategoryControllerAdvisor();
    }

    @Test
    void handleCategoryAlreadyExistsException_ShouldReturnBadRequest() {
        // Arrange
        CategoryAlreadyExistException exception = new CategoryAlreadyExistException();

        // Act
        ResponseEntity<String> response = categoryControllerAdvisor.handleCategoryAlreadyExistsException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category already exists", response.getBody());
    }

    @Test
    void handleCategoryDescriptionEmptyException_ShouldReturnBadRequest() {
        // Arrange
        CategoryDescriptionEmptyException exception = new CategoryDescriptionEmptyException();

        // Act
        ResponseEntity<String> response = categoryControllerAdvisor.handleCategoryDescriptionEmptyException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Description cannot be empty", response.getBody());
    }

    @Test
    void handleCategoryOversizeDescriptionException_ShouldReturnBadRequest() {
        // Arrange
        CategoryOversizeDescriptionException exception = new CategoryOversizeDescriptionException();

        // Act
        ResponseEntity<String> response = categoryControllerAdvisor.handleCategoryOversizeDescriptionException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Description is too long", response.getBody());
    }

    @Test
    void handleCategoryOversizeNameException_ShouldReturnBadRequest() {
        // Arrange
        CategoryOversizeNameException exception = new CategoryOversizeNameException();

        // Act
        ResponseEntity<String> response = categoryControllerAdvisor.handleCategoryOversizeNameException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is too long", response.getBody());
    }

    @Test
    void handleCategoryNameEmptyException_ShouldReturnBadRequest() {
        // Arrange
        CategoryNameEmptyException exception = new CategoryNameEmptyException();

        // Act
        ResponseEntity<String> response = categoryControllerAdvisor.handleCategoryNameEmptyException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name cannot be empty", response.getBody());
    }

}
