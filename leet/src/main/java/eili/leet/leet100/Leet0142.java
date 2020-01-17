package eili.leet.leet100;

/**
 * Problem #:  0239
 * Name:       Linked List Cycle II
 * Tags:       Linked List, Cycle Detection
 * BigO:       O(N) Time, O(1) Space
 * Difficulty: Medium
 * Techniques: Fast/Slow Pointers
 * Learnings:  Floyd's Cycle Finding Algorithm
 * 1. Use fast/slow pointers to detect a cycle
 * 2. Walk head+slow one pointer at a time to find start of cycle
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class Leet0142 {

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (slow != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return findStartOfCycle(head, slow);
            }
        }

        // no cycle
        return null;
    }

    public static ListNode findStartOfCycle(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[5];
        for (int i=0; i < 5; i++) {
            nodes[i] = new ListNode(i);
            if (i > 0) nodes[i-1].next = nodes[i];
        }
        nodes[4].next = nodes[1]; // create cycle
        System.out.println("Cycle Starts at node " + detectCycle(nodes[0]).val);
    }
}
