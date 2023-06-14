import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * The ArrayStack class implements the Stack interface using an array-based implementation.
 *
 * @param <E> the type of elements stored in the stack, must be Cloneable
 */
public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E>, Cloneable {
    private Object[] elements; // The Array stack
    private int INDEX = -1; // Index for the head of the Array stack
    private final int capacity; // The Array stack capacity

    /**
     * Constructs a new ArrayStack with the specified capacity.
     *
     * @param capacity the capacity of the stack
     * @throws NegativeCapacityException if the capacity is negative
     */
    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new NegativeCapacityException("NegativeCapacityException");
        } else {
            this.capacity = capacity;
            elements = new Object[capacity];
        }
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     * @throws StackOverflowException if the stack is already full
     */
    @Override
    public void push(E element) {
        if (capacity-1 > INDEX) {
            elements[++INDEX] = element;
        } else {
            throw new StackOverflowException("StackOverflowException");
        }
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        } else {
            E element = (E) elements[INDEX];
            elements[INDEX--] = null;
            return element;
        }
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        }
        return (E) elements[INDEX];
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return INDEX + 1;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return INDEX == -1;
    }

    /**
     * Creates and returns a shallow copy of this stack.
     *
     * @return a shallow copy of this stack
     */
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> clone;
        try {
            clone = (ArrayStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
        clone.elements = new Object[this.capacity];
        for (int i = 0; i <= this.INDEX; i++) {
            try {
                Method method = this.elements[i].getClass().getMethod("clone");
                clone.elements[i] = ((Method) method).invoke(this.elements[i]);
            } catch (Exception e) {
                return null;
            }
        }
        return clone;
    }

    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return new StackIterator(null);
        }
        return new StackIterator((E) elements[INDEX]);
    }

    /**
     * StackIterator provides an iterator over the elements in the ArrayStack.
     */
    class StackIterator implements Iterator<E> {
        private int currentIndex;

        /**
         * Constructs a new StackIterator.
         *
         * @param element the element at the current index
         */
        public StackIterator(E element) {
            if (element == null) {
                currentIndex = -1;
            } else {
                currentIndex = INDEX;
            }
        }

        /**
         * Checks if there is a next element in the iterator.
         *
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        /**
         * Returns the next element in the iterator.
         *
         * @return the next element
         * @throws EmptyStackException if there are no more elements in the iterator
         */
        @Override
        public E next() {
            if (hasNext()) {
                return (E) elements[currentIndex--];
            }
            throw new EmptyStackException("");
        }
    }
}
