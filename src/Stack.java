/**
 * The Stack interface represents a generic stack data structure.
 * It extends the Iterable interface and the Cloneable interface.
 *
 * @param <E> the type of elements stored in the stack, must be Cloneable
 */
public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    void push(E element);
    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     */
    E pop();
    /**
     * Return the element at the top of the stack.
     *
     * @return the element at the top of the stack
     */
    E peek();
    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();
    /**
     * Creates and returns a shallow copy of this stack.
     *
     * @return a shallow copy of this stack
     */
    Stack<E> clone();
}


