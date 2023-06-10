import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E>, Iterable<E>, Cloneable {
    private Object[] elements; // The Array stack
    private int INDEX = -1; // Index for the head of the Array stack
    private final int capacity; // The Array stack capacity

    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new NegativeCapacityException("NegativeCapacityException");
        } else {
            this.capacity = capacity;
            elements = new Object[capacity];
        }
    }

    @Override
    public void push(E element) {
        if (capacity-1 > INDEX) {
            elements[++INDEX] = element;
        } else {
            throw new StackOverflowException("StackOverflowException");
        }
    }

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

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        }
        return (E) elements[INDEX];
    }

    @Override
    public int size() {
        return INDEX + 1;
    }

    @Override
    public boolean isEmpty() {
        return INDEX == -1;
    }

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

    public Iterator<E> iterator() {
        if (isEmpty()) {
            return new StackIterator(null);
        }
        return new StackIterator((E) elements[INDEX]);
    }

    class StackIterator implements Iterator<E> {
        private int currentIndex;

        public StackIterator(E element) {
            if (element == null) {
                currentIndex = -1;
            } else {
                currentIndex = INDEX;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            if (hasNext()) {
                return (E) elements[currentIndex--];
            }
            throw new EmptyStackException("");
        }
    }
}
