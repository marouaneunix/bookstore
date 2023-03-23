package ma.norsys.bookstore.exceptions;

public class InvalidRequestException extends Exception {

    public InvalidRequestException(String message) {
        super(message);
    }
}
