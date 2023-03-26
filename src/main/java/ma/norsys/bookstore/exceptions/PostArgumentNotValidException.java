package ma.norsys.bookstore.exceptions;

public class PostArgumentNotValidException extends RuntimeException{

    public PostArgumentNotValidException(String msg){
        super(msg);
    }
}
