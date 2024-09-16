package bootcampragma.emazon.domain.exception.category;

import bootcampragma.emazon.domain.util.Constants;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super(Constants.CATEGORY_NOT_FOUND_EXCEPTION);
    }
}
