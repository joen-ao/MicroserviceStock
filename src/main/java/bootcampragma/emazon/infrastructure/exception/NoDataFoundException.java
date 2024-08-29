package bootcampragma.emazon.infrastructure.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(){
        super("No found categories");
    }

}
