package eili.leet;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode makeList(int[] vals) {
        ListNode[] nodes = new ListNode[vals.length];
        nodes[0] = new ListNode(vals[0]);
        for (int i=1; i < vals.length; i++) {
            nodes[i] = new ListNode(vals[i]);
            nodes[i-1].next = nodes[i];
        }

        return nodes[0];
    }

    public static ListNode makeList(int size) {
        ListNode[] nodes = new ListNode[10];
        nodes[0] = new ListNode(0);
        for (int i=1; i < 10; i++) {
            nodes[i] = new ListNode(i);
            nodes[i-1].next = nodes[i];
        }

        return nodes[0];
    }

    public static void printList(ListNode runr) {
        while (runr != null) {
            System.out.print(runr.val);
            runr = runr.next;
        }
        System.out.println();
    }
}
