package eili.ds;

import java.util.Iterator;
import java.util.List;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.add(5,3,7,1,4,6,10,0,2);
        print(bt);

        List<String> inOrder = bt.visitInOrder((Integer anInt) -> anInt + "");
        System.out.print("InOrder: ");
        for (String aStr : inOrder) System.out.print(aStr + ",");
        System.out.println();

        List<String> preOrder = bt.visitPreOrder((Integer anInt) -> anInt + "");
        System.out.print("PreOrder: ");
        for (String aStr : preOrder) System.out.print(aStr + ",");
        System.out.println();

        List<String> postOrder = bt.visitPostOrder((Integer anInt) -> anInt + "");
        System.out.print("PostOrder: ");
        for (String aStr : postOrder) System.out.print(aStr + ",");
        System.out.println();


        System.out.println("Removing 5: " + bt.remove(5) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 4: " + bt.remove(4) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 7: " + bt.remove(7) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 10: " + bt.remove(10) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 1: " + bt.remove(1) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 3: " + bt.remove(3) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 2: " + bt.remove(2) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 6: " + bt.remove(6) + " size is now: " + bt.size());
        print(bt);
        System.out.println("Removing 0: " + bt.remove(0) + " size is now: " + bt.size());
        print(bt);

    }


    public static void print(BinaryTree<?> bt) {
        for (Iterator<?> iter = bt.iterator(); iter.hasNext(); ) {
            System.out.print(iter.next());
            if (iter.hasNext()) System.out.print(","); else System.out.println();
        }
    }
}
