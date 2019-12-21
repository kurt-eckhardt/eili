package eili.leet.leet000;

import eili.leet.ListNode;

import static eili.leet.ListNode.makeList;
import static eili.leet.ListNode.printList;

/**
 * Problem #:  0024
 * Name:       Swap Pairs
 * Tags:       Linked List
 * BigO:       O(N)
 * Difficulty: Medium
 * Techniques: Dummy Pointer
 * Learnings:
 * 1) I had the right algorithm in mind, but it took me 1.5 hours to implement correctly.  Why!?
 *
 *
 * Given a linked list, swap every two adjacent nodes and return its head.  You may not modify
 * the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class Leet0024 {

    public static void main(String[] args) {
        printList(swapPairs2(makeList(new int[] {1,2,3,4})));
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev  = dummy;
        ListNode first = head;
        while (first != null && first.next != null) {
            prev.next = swap(first, first.next);
            prev  = first;
            first = first.next;
        }

        return dummy.next;
    }


    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev  = dummy;
        ListNode first = head;
        while (first != null && first.next != null) {
            ListNode second = first.next;
            ListNode new1st = swap(first, second);
            prev.next = new1st;
            prev  = new1st.next;
            first = prev.next;
        }

        return dummy.next;
    }

    public static ListNode swap(ListNode first, ListNode second) {
        first.next  = second.next;
        second.next = first;
        return second;
    }


}
