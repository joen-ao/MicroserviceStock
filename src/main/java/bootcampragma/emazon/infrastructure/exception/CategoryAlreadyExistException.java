package bootcampragma.emazon.infrastructure.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Category already exists");
    }

}
