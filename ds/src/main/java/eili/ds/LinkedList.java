package eili.ds;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> {

    private Node head;
    private Node tail;
    private int size;


    /**
     * Number of items in the list
     * @return
     */
    public int size() {
        return size;
    }


    /**
     * Adds an item to the end of the list
     * @param data
     */
    public void add(T data) {
        Node toAdd = new Node(data);
        if (tail == null) {
            tail = toAdd;
        } else {
            tail.next = toAdd;
            toAdd.prev = tail;
            tail = toAdd;
        }

        if (head == null) head = toAdd;
        size++;
    }

    public void add(T... data) {
        for (T item : data) add(item);
    }


    /**
     * Adds an item to the front of the list
     * @param data
     */
    public void addFirst(T data) {
        Node toAdd = new Node(data);
        if (head == null) {
            head = toAdd;
        } else {
            head.prev = toAdd;
            toAdd.next = head;
            head = toAdd;
        }

        if (tail == null) tail = toAdd;
        size++;
    }


    /**
     * Removes and returns the last item in the list
     * @return
     */
    public T remove() {
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


    /**
     * Removes and returns the first item in the list
     * @return
     */
    public T removeFirst() {
        if (tail == head) tail = null;
        if (head != null) {
            T toReturn = head.data;
            head = head.next;
            if (head != null) head.prev = null;
            size--;
            return toReturn;
        } else {
            return null;
        }
    }


    /**
     * Removes the first occurrence of toRemove from the list.
     * @param toRemove
     * @return
     */
    public boolean remove(T toRemove) {
        Node runner = head;
        while (runner != null) {
            if (runner.data.equals(toRemove)) {
                if (runner.prev != null) runner.prev.next = runner.next;
                if (runner.next != null) runner.next.prev = runner.prev;
                if (runner == head) head = head.next;
                if (runner == tail) tail = tail.prev;
                size--;
                return true;
            } else {
                runner = runner.next;
            }
        }
        return false;
    }


    /**
     * Returns a cursor that can traverse the LinkedList
     * forwards and backwards.
     *
     * @return
     */
    public ListIterator<T> iterator() {
        return new ListIterator<T>() {

            int index   = -1;
            Node prev = null;
            Node next = head;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T toReturn = next.data;
                    prev = next;
                    next = next.next;
                    index++;
                    return toReturn;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return prev != null;
            }

            @Override
            public T previous() {
                if (hasPrevious()) {
                    T toReturn = prev.data;
                    next = prev;
                    prev = prev.prev;
                    index--;
                    return toReturn;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                return index+1;
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


    public void print() {
        Node runner = head;
        while (runner != null) {
            System.out.println(runner.data);
            runner = runner.next;
        }
    }


    private class Node {
        Node next;
        Node prev;
        T data;

        Node(T data) {
            this.data = data;
        }
    }
}
