/**
 * The EmptyStackException class is a custom exception that represents an exception thrown when performing an operation on an empty stack.
 * It extends the StackException class.
 */
public class EmptyStackException extends StackException {
    /**
     * Constructs a new EmptyStackException with no specified error message.
     */
    public EmptyStackException() {
        super();
    }

    /**
     * Constructs a new EmptyStackException with the specified error message and cause.
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new EmptyStackException with the specified error message.
     *
     * @param message the error message
     */
    public EmptyStackException(String message) {
        super(message);
    }
}
