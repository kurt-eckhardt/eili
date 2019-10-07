package eili.ds;

import java.util.ListIterator;

public class DynamicArrayTest {
    public static void main(String[] args) {
        DynamicArray<Integer> da = new DynamicArray<>(4);
        da.add(5);
        System.out.println("da.size()="+da.size() + " da.capacity()="+da.capacity());

        da.add(6,7,8);
        System.out.println("da.size()="+da.size() + " da.capacity()="+da.capacity());

        Integer[] moreIntegers = new Integer[] { 3,8,9 };
        da.add(moreIntegers);
        System.out.println("da.size()="+da.size() + " da.capacity()="+da.capacity());
        print(da);
        System.out.println("Removing " + da.remove() + " size is now: " + da.size());
        print(da);
        System.out.println("Removing " + da.remove() + " size is now: " + da.size());
        print(da);
        System.out.println("Removing " + da.remove() + " size is now: " + da.size());
        print(da);
        System.out.println("Removing " + da.remove() + " size is now: " + da.size());
        print(da);

        da.add(20,30,40,50,60);
        System.out.println("da.size()="+da.size() + " da.capacity()="+da.capacity());
        print(da);
    }


    public static void print(DynamicArray<?> da) {
        for (ListIterator<?> iter = da.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }}
