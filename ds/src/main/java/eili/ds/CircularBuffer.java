package eili.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Holds N items.
// Behavior:
// 1. Writes newest to the end of the buffer
// 2. Reads from oldest to newest data
// 3. Overwrites oldest unread data when new writes come in and pushes read pointer forward.
// 0 1 2 3 4 5 6 7 8 9
// W
// R
public class CircularBuffer<T> {

    private int wIdx; // Holds the index where the next piece of data will be written
    private int rIdx; // Holds the index where the next piece of data will be read
    private int size; // The number of unread data items from 0 up to capacity
    private int capa; // The maximum number of items that may be held in the buffer
    private Object[] data;

    public CircularBuffer(int capacity) {
        this.capa = capacity;
        this.data = new Object[capacity];
    }


    public void write(T item) {

        data[wIdx] = item;
        advanceWritePointer();

        // Advance the read pointer if the buffer is full
        if (size == capa) {
            advanceReadPointer();
        } else {
            size++;
        }
    }


    public void write(T... items) {
        for (T item : items) write(item);
    }


    /**
     * @return
     */
    public T read() {
        if (size > 0) {
            T item = (T)data[rIdx];
            data[rIdx] = null;
            advanceReadPointer();
            size--;
            return item;
        } else {
            return null;
        }
    }

    private void advanceWritePointer() {
        wIdx++;
        if (wIdx >= capa) wIdx = 0;
    }

    private void advanceReadPointer() {
        rIdx++;
        if (rIdx >= capa) rIdx = 0;
    }


    public void print() {
        System.out.println("capa=" + capa + " size="+size + " wIdx="+wIdx + " rIdx="+rIdx);
        for (int i=0; i < capa; i++) {
            char itemMarker = 'D';
            if (data[i] == null) itemMarker = 'X';
            System.out.print(itemMarker + " ");
        }
        System.out.println();
        for (int i=0; i < capa; i++) {
            char writeMarker = ' ';
            if (i == wIdx) writeMarker = 'W';
            System.out.print(writeMarker + " ");
        }
        System.out.println();
        for (int i=0; i < capa; i++) {
            char readMarker = ' ';
            if (i == rIdx) readMarker = 'R';
            System.out.print(readMarker + " ");
        }
        System.out.println();
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int readIdx   = rIdx;
            int numUnread = size;

            @Override
            public boolean hasNext() {
                return numUnread > 0;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T next = (T)data[readIdx++];
                    if (readIdx >= capa) readIdx = 0;
                    numUnread--;
                    return next;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
