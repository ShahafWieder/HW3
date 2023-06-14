/**
 * The StackOverflowException class is a custom exception that represents an exception thrown when a stack overflows.
 * It extends the StackException class.
 */
public class StackOverflowException extends StackException {
    /**
     * Constructs a new StackOverflowException with no specified error message.
     */
    public StackOverflowException() {
        super();
    }

    /**
     * Constructs a new StackOverflowException with the specified error message and cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new StackOverflowException with the specified error message.
     *
     * @param message the error message
     */
    public StackOverflowException(String message) {
        super(message);
    }
}
