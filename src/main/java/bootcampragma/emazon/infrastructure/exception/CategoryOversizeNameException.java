package bootcampragma.emazon.infrastructure.exception;

public class CategoryOversizeNameException extends RuntimeException{
    public CategoryOversizeNameException(){
        super("Name is too long");
    }
}
