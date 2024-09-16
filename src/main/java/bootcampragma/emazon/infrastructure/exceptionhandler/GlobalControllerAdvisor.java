package bootcampragma.emazon.infrastructure.exceptionhandler;

import bootcampragma.emazon.domain.exception.article.CategoriesNullException;
import bootcampragma.emazon.domain.exception.article.CategoriesSizeException;
import bootcampragma.emazon.domain.exception.article.DuplicateCategoriesException;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;
import bootcampragma.emazon.domain.exception.brand.BrandNotFoundException;
import bootcampragma.emazon.domain.exception.category.CategoryAlreadyExistException;
import bootcampragma.emazon.domain.exception.category.CategoryNotFoundException;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvisor {

    private static final String ERROR_KEY = "error";

    @ExceptionHandler(DuplicateCategoriesException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCategoriesException(DuplicateCategoriesException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoriesSizeException.class)
    public ResponseEntity<Map<String, String>> handleCategoriesSizeException(CategoriesSizeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistException(BrandAlreadyExistException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFoundException(BrandNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(CategoryAlreadyExistException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSortDirectionException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSortDirectionException(InvalidSortDirectionException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoriesNullException.class)
    public ResponseEntity<Map<String, String>> handleCategoriesNullException(CategoriesNullException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
