package bootcampragma.emazon.infrastructure.input.rest;
import bootcampragma.emazon.infrastructure.exception.*;
import bootcampragma.emazon.infrastructure.exceptionhandler.CategoryControllerAdvisor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryRestControllerTest {

    @Test
    void handleCategoryAlreadyExistsException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryAlreadyExistException ex = new CategoryAlreadyExistException();

        ResponseEntity<String> response = advisor.handleCategoryAlreadyExistsException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Category already exists", response.getBody());
    }
    @Test
    void handleCategoryNameEmptyException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryNameEmptyException ex = new CategoryNameEmptyException();

        ResponseEntity<String> response = advisor.handleCategoryNameEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name cannot be empty", response.getBody());
    }

    @Test
    void handleCategoryDescriptionEmptyException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryDescriptionEmptyException ex = new CategoryDescriptionEmptyException();

        ResponseEntity<String> response = advisor.handleCategoryDescriptionEmptyException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Description cannot be empty", response.getBody());
    }

    @Test
    void handleCategoryOversizeDescriptionException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryOversizeDescriptionException ex = new CategoryOversizeDescriptionException();

        ResponseEntity<String> response = advisor.handleCategoryOversizeDescriptionException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Description is too long", response.getBody());
    }

    @Test
    void handleCategoryOversizeNameException_ShouldReturnBadRequest() {
        CategoryControllerAdvisor advisor = new CategoryControllerAdvisor();
        CategoryOversizeNameException ex = new CategoryOversizeNameException();

        ResponseEntity<String> response = advisor.handleCategoryOversizeNameException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is too long", response.getBody());
    }
}