package bootcampragma.emazon.infrastructure.exception;

public class CategoryOversizeDescriptionException extends RuntimeException{
    public CategoryOversizeDescriptionException(){
        super("Description is too long");
    }

}
