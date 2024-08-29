package bootcampragma.emazon.infrastructure.exception;

public class CategoryDescriptionEmptyException extends RuntimeException{
    public CategoryDescriptionEmptyException(){
        super("Description cannot be empty");
    }

}
