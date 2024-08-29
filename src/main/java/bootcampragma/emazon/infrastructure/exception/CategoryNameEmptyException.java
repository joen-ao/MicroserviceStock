package bootcampragma.emazon.infrastructure.exception;

public class CategoryNameEmptyException extends RuntimeException{
    public CategoryNameEmptyException(){
        super("Name cannot be empty");
    }

}
