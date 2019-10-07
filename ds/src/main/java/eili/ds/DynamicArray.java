package eili.ds;

import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Possile speed improvements if capacity is aligned to 4k boundaries?
 *
 * @param <T>
 */
public class DynamicArray<T> {

    public static final int DEFAULT_CAPACITY = 128;

    private int size;
    private int capa;
    private T[] array;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        this.size  = 0;
        this.capa  = capacity;
        this.array = (T[])new Object[capa];
    }

    public void add(T data) {
        ensureCapacity(1);
        array[size++] = data;
    }

    public void add(T... data) {
        ensureCapacity(data.length);
        for (T item : data) array[size++] = item;
    }

    public T remove() {
        if (size > 0) {
            return array[--size];
        } else {
            throw new NoSuchElementException();
        }
    }

    public T get(int i) {
        if (i >= 0 && i < size) {
            return array[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capa;
    }

    public ListIterator<T> iterator() {
        return new ListIterator<T>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return array[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                if (hasPrevious()) {
                    return array[--index];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index-1;
            }

            @Override
            public void remove() { ; }

            @Override
            public void set(T t) { ; }

            @Override
            public void add(T t) { ; }
        };
    }

    /**
     * Grows the array if the amount of space requested
     * is more than the current capacity.  The capacity
     * is increased by 2x more than what is needed to
     * copy over all existing data + newly requested
     * so that the O(N) growth cost over is amoritzed
     * to O(1) over the next O(N) inserts.
     * @param requested
     */
    private void ensureCapacity(int requested) {
        int available = capa - size;
        if (requested > available) {
            int needed = requested - available;
            int newCap = (size + needed) * 2;
            T[] newArr = (T[])new Object[newCap];

            System.arraycopy(this.array, 0, newArr, 0, size);
            this.capa  = newCap;
            this.array = newArr;
        }
    }
}
