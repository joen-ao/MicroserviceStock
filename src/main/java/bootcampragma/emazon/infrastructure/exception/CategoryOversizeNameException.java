package bootcampragma.emazon.infrastructure.exception;

public class CategoryOversizeNameException extends RuntimeException{
    public CategoryOversizeNameException(){
        super("The category name is too long");
    }
}
