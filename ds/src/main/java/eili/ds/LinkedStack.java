package eili.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stack implemented using an underlying list of linked nodes.
 *
 * @param <T>
 */
public class LinkedStack<T> implements Stack<T> {

    private Node head; // bottom
    private Node tail; // top
    private int size;


    public void push(T data) {
        Node toAdd = new Node(data);
        if (tail != null) {
            tail.next  = toAdd;
            toAdd.prev = tail;
            tail = toAdd;
        } else {
            tail = toAdd;
        }
        if (head == null) head = toAdd;
        size++;
    }


    public void push(T... data) {
        for (T item : data) push(item);
    }


    public T pop() {
        if (head == tail) head = null;
        if (tail != null) {
            T toReturn = tail.data;
            tail = tail.prev;
            if (tail != null) tail.next = null;
            size--;
            return toReturn;
        } else {
            return null;
        }
    }

    public int size() {
        return size;
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node next = tail;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = next.data;
                    next = next.prev;
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }


    class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }
}
