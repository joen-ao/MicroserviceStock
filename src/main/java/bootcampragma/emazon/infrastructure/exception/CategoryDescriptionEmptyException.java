package bootcampragma.emazon.infrastructure.exception;

public class CategoryDescriptionEmptyException extends RuntimeException{
    public CategoryDescriptionEmptyException(){
        super("the category description is empty");
    }
}
