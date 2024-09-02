package bootcampragma.emazon.domain.exception.article;

public class DuplicateCategoriesException extends RuntimeException {
    public DuplicateCategoriesException() {
        super("Duplicate categories are not allowed");
    }
}
