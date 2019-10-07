package eili.ds;

import java.util.Iterator;

public class HeapTest {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(8);
        heap.add(5,2,10,3,22,4,11,17,9,28,42,12,3);
        heap.print();

        print(heap);


//        System.out.println("heap.size()="+heap.size());
//        print(heap);
//        System.out.println("Removing: " + heap.pop() + " size is now: " + heap.size());
//        print(heap);
//        System.out.println("Removing: " + heap.pop() + " size is now: " + heap.size());
//        print(heap);
//        System.out.println("Removing: " + heap.pop() + " size is now: " + heap.size());
//        print(heap);
    }

    public static void print(Heap<?> heap) {
        for (Iterator<?> iter = heap.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }
}
