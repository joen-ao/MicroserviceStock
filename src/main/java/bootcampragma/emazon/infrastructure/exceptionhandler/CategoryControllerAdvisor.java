package bootcampragma.emazon.infrastructure.exceptionhandler;

import bootcampragma.emazon.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryControllerAdvisor {

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<String>handleCategoryAlreadyExistsException(CategoryAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryDescriptionEmptyException.class)
    public ResponseEntity<String> handleCategoryDescriptionEmptyException(CategoryDescriptionEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryOversizeDescriptionException.class)
    public ResponseEntity<String> handleCategoryOversizeDescriptionException(CategoryOversizeDescriptionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryOversizeNameException.class)
    public ResponseEntity<String> handleCategoryOversizeNameException(CategoryOversizeNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNameEmptyException.class)
    public ResponseEntity<String> handleCategoryNameEmptyException(CategoryNameEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
