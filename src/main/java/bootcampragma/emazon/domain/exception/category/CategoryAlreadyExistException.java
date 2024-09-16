package bootcampragma.emazon.domain.exception.category;

import bootcampragma.emazon.domain.util.Constants;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super(Constants.CATEGORY_ALREADY_EXIST_EXCEPTION);
    }

}
