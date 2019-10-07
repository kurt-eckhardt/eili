package eili.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack implemented on top of a fixed size array
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack<T> {

    private int size;
    private T[] array;

    public ArrayStack(int capacity) {
        this.size = 0;
        this.array = (T[])new Object[capacity];
    }

    @Override
    public void push(T data) {
        if (size < capacity()) {
            array[size++] = data;
        }
    }

    public void push(T... data) {
        for (T item : data) push(item);
    }

    @Override
    public T pop() {
        if (size > 0) {
            return array[--size];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = size;

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return array[--index];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
