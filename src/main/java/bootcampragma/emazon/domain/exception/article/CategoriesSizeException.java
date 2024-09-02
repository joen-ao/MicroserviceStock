package bootcampragma.emazon.domain.exception.article;

public class CategoriesSizeException extends RuntimeException {
    public CategoriesSizeException() {
        super("Categories size must be between 1 and 3");
    }
}
