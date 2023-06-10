/**
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E> {
    private E[] elements;
    private int size;
    private int capacity;

    public ArrayStack(int capacity) {
        elements = (E[]) new Object[capacity];
        size = this.elements.length;
    }

    @Override
    public void push(E element) {
        if (capacity > size) {
            elements[size] = element;
        }
        else {
            throw new StackOverflowException("StackOverflowException");
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        }
        E element = elements[--size];
        elements[size] = null;
        return element;
    }
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        }
        return elements[--size];
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> clone = (ArrayStack<E>) super.clone();
            clone.elements = elements.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = capacity - 1;
            public boolean hasNext() {
                return index >= 0;
            }
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index--];
            }
        };
    }
}
*/