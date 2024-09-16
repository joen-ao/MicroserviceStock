package bootcampragma.emazon.domain.exception.brand;

import bootcampragma.emazon.domain.util.Constants;

public class BrandAlreadyExistException extends RuntimeException{
    public BrandAlreadyExistException(){
        super(Constants.BRAND_ALREADY_EXIST_EXCEPTION);
    }
}
