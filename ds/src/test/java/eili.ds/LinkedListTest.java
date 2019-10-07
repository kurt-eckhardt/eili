package eili.ds;

import java.util.ListIterator;

public class LinkedListTest {
    
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(5);
        ll.add(6,7,8);
        Integer[] moreIntegers = new Integer[] { 3,8,9 };
        ll.add(moreIntegers);

        System.out.println("ll.size()="+ll.size());
        print(ll);
        System.out.println("Removing 17: " + ll.remove(17) + " size is now: " + ll.size());
        print(ll);
        System.out.println("Removing 5: " + ll.remove(5) + " size is now: " + ll.size());
        print(ll);
        System.out.println("Removing 8: " + ll.remove(8) + " size is now: " + ll.size());
        print(ll);
        System.out.println("Removing 9: " + ll.remove(9) + " size is now: " + ll.size());
        print(ll);
    }


    public static void print(LinkedList<?> ll) {
        for (ListIterator<?> iter = ll.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }
}