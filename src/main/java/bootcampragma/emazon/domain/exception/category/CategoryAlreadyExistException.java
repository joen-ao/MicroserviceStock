package bootcampragma.emazon.domain.exception.category;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Category already exists");
    }

}
