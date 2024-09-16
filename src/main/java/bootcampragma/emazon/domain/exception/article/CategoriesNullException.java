package bootcampragma.emazon.domain.exception.article;

import bootcampragma.emazon.domain.util.Constants;

public class CategoriesNullException extends RuntimeException {
    public CategoriesNullException() {
        super(Constants.CATEGORY_NULL_EXCEPTION);
    }
}
