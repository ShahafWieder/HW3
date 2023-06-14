/**
 * The SongAlreadyExistsException class is a custom exception that represents an exception thrown when trying to add a song that already exists.
 * It extends the RuntimeException class.
 */
public class SongAlreadyExistsException extends RuntimeException{
    /**
     * Constructs a new SongAlreadyExistsException with the specified error message.
     *
     * @param message the error message
     */
    public SongAlreadyExistsException(String message){
        super(message);
    }
}
