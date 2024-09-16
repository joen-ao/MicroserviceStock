package bootcampragma.emazon.domain.exception.article;

import bootcampragma.emazon.domain.util.Constants;

public class DuplicateCategoriesException extends RuntimeException {
    public DuplicateCategoriesException() {
        super(Constants.DUPLICATE_CATEGORIES_EXCEPTION);
    }
}
