package bootcampragma.emazon.domain.exception.brand;

public class BrandAlreadyExistException extends RuntimeException{
    public BrandAlreadyExistException(){
        super("Brand already exist");
    }
}
