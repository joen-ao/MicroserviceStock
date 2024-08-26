package bootcampragma.emazon.infrastructure.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("There is already a category with that name");
    }
}
