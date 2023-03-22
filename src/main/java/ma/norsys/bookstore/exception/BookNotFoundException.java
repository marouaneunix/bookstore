package ma.norsys.bookstore.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(Long id) {
        super("Book with id: "+ id +" not found");
    }
}
