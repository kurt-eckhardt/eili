package eili.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> implements Cloneable {

    public static final int DEFAULT_CAPACITY = 16;

    private int size;
    private int capa;
    private Object[] array;

    public Heap() {
        this(DEFAULT_CAPACITY);
    }

    public Heap(int capacity) {
        this.size  = 0;
        this.capa  = capacity;
        this.array = new Object[capa];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capa;
    }

    public void add(T data) {
        ensureCapacity();
        array[size] = data;
        siftUp(size);
        size++;
    }

    public void add(T... data) {
        for (T item : data) add(item);
    }

    public T remove() {
        if (size > 0) {
            T toReturn = get(0);
            heapify(0);
            size--;
            return toReturn;
        } else {
            throw new NoSuchElementException();
        }
    }

    // remember parent index = child index - 1 for a base 0 array
    private void siftUp(int cIdx) {
        int pIdx = (cIdx-1) / 2;
        while (cIdx > 0) {
            int compare = get(pIdx).compareTo(get(cIdx));
            if (compare < 0) {
                swap(pIdx, cIdx);
                cIdx = pIdx;
                pIdx = (cIdx-1) / 2;
            } else {
                return;
            }
        }
    }

    // Move the smallest element in the heap down
    // (starting from pIdx) until the heap property
    // is restored.
    private void heapify(int pIdx) {

        // move the smallest element up to the top (or pIdx)
        array[pIdx]   = get(size-1);
        array[size-1] = null;

        // now push it back down to where it should live.
        while (true) {
            int maxIdx = pIdx;
            int lIdx   = pIdx * 2 + 1;
            int rIdx   = pIdx * 2 + 2;
            if (get(lIdx) != null && get(lIdx).compareTo(get(maxIdx)) > 0) maxIdx = lIdx;
            if (get(rIdx) != null && get(rIdx).compareTo(get(maxIdx)) > 0) maxIdx = rIdx;
            if (maxIdx != pIdx) {
                swap(pIdx, maxIdx);
                pIdx = maxIdx;
            } else {
                break;
            }
        }
    }

    private T get(int idx) {
        if (idx < capa) {
            return (T)array[idx];
        } else {
            return null;
        }
    }


    public void print() {
        for (Object item : array) System.out.print(item + ",");
        System.out.println();
    }

    private void swap(int idx1, int idx2) {
        T temp = get(idx1);
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    private void ensureCapacity() {
        if (size >= capa) {
            Object[] newArray = new Object[capa*2];
            System.arraycopy(array, 0, newArray, 0, size);
            capa  = capa * 2;
            array = newArray;
        }
    }


    @Override
    public Heap clone() {
        try {
            Heap clone = (Heap)super.clone();
            clone.array = this.array;
            clone.size  = this.size;
            clone.capa  = this.capa;
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    public Iterator<T> iterator() {
        Heap clone = this.clone();
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return clone.size() > 0;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return (T)clone.remove();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
