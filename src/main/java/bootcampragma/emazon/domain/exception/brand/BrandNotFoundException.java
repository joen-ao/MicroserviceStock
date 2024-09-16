package bootcampragma.emazon.domain.exception.brand;

import bootcampragma.emazon.domain.util.Constants;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super(Constants.BRAND_NOT_FOUND_EXCEPTION);
    }
}
