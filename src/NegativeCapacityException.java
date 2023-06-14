/**
 * The NegativeCapacityException class is a custom exception that represents an exception thrown when a negative capacity is provided for a stack.
 * It extends the StackException class.
 */
public class NegativeCapacityException extends StackException{
    /**
     * Constructs a new NegativeCapacityException with the specified error message.
     *
     * @param message the error message
     */
    public NegativeCapacityException(String message){
        super(message);
    }
}
