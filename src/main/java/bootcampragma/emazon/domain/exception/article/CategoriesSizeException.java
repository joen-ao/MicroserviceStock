package bootcampragma.emazon.domain.exception.article;


import bootcampragma.emazon.domain.util.Constants;

public class CategoriesSizeException extends RuntimeException {
    public CategoriesSizeException() {
        super(Constants.CATEGORY_SIZE_EXCEPTION);
    }
}
