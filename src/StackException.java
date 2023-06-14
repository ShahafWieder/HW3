/**
 * The StackException class is a custom exception that represents an exception specific to stack operations.
 * It extends the RuntimeException class.
 */
public class StackException extends RuntimeException{
    /**
     * Constructs a new StackException with the specified error message.
     *
     * @param message the error message
     */
    public StackException(String message){
        super(message);
    }
}
