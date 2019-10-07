package eili.ds;

import java.util.Iterator;


public class LinkedStackTest {

    public static void main(String[] args) {
        LinkedStack<Integer> ls = new LinkedStack<>();
        ls.push(5);
        ls.push(6,7,8);
        Integer[] moreIntegers = new Integer[] { 3,8,9 };
        ls.push(moreIntegers);

        System.out.println("ls.size()="+ls.size());
        print(ls);
        System.out.println("Removing: " + ls.pop() + " size is now: " + ls.size());
        print(ls);
        System.out.println("Removing: " + ls.pop() + " size is now: " + ls.size());
        print(ls);
        System.out.println("Removing: " + ls.pop() + " size is now: " + ls.size());
        print(ls);
    }


    public static void print(LinkedStack<?> ls) {
        for (Iterator<?> iter = ls.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }
}