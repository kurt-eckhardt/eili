package eili.ds;

import java.util.Iterator;

/**
 * Stack implemented on top of a dynamic array
 *
 * @param <T>
 */
public class DynamicStack<T> implements Stack<T> {

    private DynamicArray<T> underlying;

    public DynamicStack() {
        underlying = new DynamicArray<>();
    }

    @Override
    public void push(T data) {
        underlying.add(data);
    }

    public void push(T... data) {
        underlying.add(data);
    }

    @Override
    public T pop() {
        return underlying.remove();
    }

    @Override
    public int size() {
        return underlying.size();
    }

    public Iterator<T> iterator() {
        return underlying.iterator();
    }
}