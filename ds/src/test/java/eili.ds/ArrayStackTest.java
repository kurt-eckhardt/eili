package eili.ds;

import java.util.Iterator;


public class ArrayStackTest {

    public static void main(String[] args) {
        ArrayStack<Integer> as = new ArrayStack<>(10);
        as.push(5);
        as.push(6,7,8);
        Integer[] moreIntegers = new Integer[] { 3,8,9 };
        as.push(moreIntegers);

        System.out.println("as.size()="+as.size());
        print(as);
        System.out.println("Removing: " + as.pop() + " size is now: " + as.size());
        print(as);
        System.out.println("Removing: " + as.pop() + " size is now: " + as.size());
        print(as);
        System.out.println("Removing: " + as.pop() + " size is now: " + as.size());
        print(as);
    }


    public static void print(ArrayStack<?> as) {
        for (Iterator<?> iter = as.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }
}