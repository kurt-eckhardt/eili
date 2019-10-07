package eili.ds;

import java.util.Iterator;

public class CircularBufferTest {

    public static void main(String[] args) {
        CircularBuffer<Integer> cb = new CircularBuffer<>(10);
        cb.print();
        cb.write(0,1,2,3,4,5,6,7,8,9);
        System.out.println("Read=" + cb.read());
        cb.print();
        cb.write(10);
        cb.print();
        Iterator<Integer> iter = cb.iterator();
        while (iter.hasNext()) {
            System.out.println("Read from iter=" + iter.next());
        }
        cb.print();
        Integer read = cb.read();
        while (read != null) {
            System.out.println("Read from buffer=" + read);
            read = cb.read();
        }
        cb.print();
    }

}