import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E> , Iterable<E>{
    private final int DEFAULT_SIZE = 0; // The minimal Array stack size
    private E[] elements; // The Array stack
    private int size; // The Array stack current size
    private int INDEX = 0; // Index for the head of the Array stack
    private final int capacity; // The Array stack capacity

    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new NegativeCapacityException("NegativeCapacityException");
        }
        this.capacity = capacity;
        if(capacity==0){
            size = 0;
        }
        else {
            size = DEFAULT_SIZE;
            elements = (E[]) new ArrayStack[size];
        }
    }

    @Override
    public void push(E element) {
        if(capacity > size) {
            if (size == INDEX) {
                ArrayStack<E> clone = this.clone();
                E[] temp = (E[]) new ArrayStack[(size+1) * 2];
                temp[size] = element;
                clone.elements = temp;
                clone.size = size * 2;
                this.elements = clone.elements;
                INDEX++;
            } else if (size > INDEX) {
                elements[INDEX++] = element;
                INDEX++;
            }
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
        E element = elements[INDEX];
        elements[INDEX] = null;
        INDEX--;
        return element;
    }
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException("EmptyStackException");
        }
        return elements[INDEX];
    }
    @Override
    public int size() {
        return size; // for now returns the size of the stack array including empty cells, can be changed to INDEX for amount of elements different from null
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
        return new StackIterator(elements[INDEX]);
    }


    class StackIterator implements Iterator<E> {
        private int currentIndex;
        public StackIterator(E element) {
            elements[currentIndex] = element;
            currentIndex = INDEX;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {

            if (hasNext()) {
                return elements[currentIndex--];
            }
            throw new EmptyStackException("");
        }
    }
}