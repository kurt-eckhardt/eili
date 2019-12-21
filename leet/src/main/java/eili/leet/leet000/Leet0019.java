package eili.leet.leet000;

import eili.leet.ListNode;

import static eili.leet.ListNode.makeList;
import static eili.leet.ListNode.printList;

/**
 * Problem #:  0019
 * Name:       Remove Nth Node From End of List
 * Tags:       Linked List
 * Difficulty: Medium
 * Techniques: Dummy Pointer, Two-Pointers
 * Learnings:
 * 1) An alternate solution creates a "dummy pointer" infront of head so that we can
 *    remove the nth node without destructively changing its value to the next node.
 *
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Follow up: Could you do this in one pass?
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Leet0019 {

    public static void main(String[] args) {

        for (int i=0; i < 11; i++) {
            System.out.print("i="+i + " ");
            printList(removeNthFromEnd(makeList(10), i));
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        n--;
        if (head == null || n < 0) {
            return null;
        } else if (n == 0) {
            return removeLastNode(head);
        } else {
            return removeNthFromLastNode(head, n);
        }
    }


    // Special case to remove the last node.
    // we need to keep a pointer to the node before the
    // last one so that we can unlink it from the list.
    public static ListNode removeLastNode(ListNode head) {

        ListNode lastPrev = null;
        ListNode lastNode = head;
        while (lastNode.next != null) {
            lastPrev = lastNode;
            lastNode = lastNode.next;
        }

        // LastPrev will be null when we have just a single node in the list.
        if (lastPrev == null) {
            return null;
        } else {
            lastPrev.next = null;
            return head;
        }
    }


    // "Removes" the nth from last by reassigning its value to the next
    // node in the list.  This could be dangerous if there are pointers
    // that are watching this or the next node's value.
    public static ListNode removeNthFromLastNode(ListNode head, int n) {
        ListNode nthFromLast = head;
        ListNode lastNode    = head;

        while (n > 0 && lastNode.next != null) {
            lastNode = lastNode.next;
            n--;
        }

        if (n > 0) {
            return null;
        }

        while (lastNode.next != null) {
            lastNode = lastNode.next;
            nthFromLast = nthFromLast.next;
        }

        nthFromLast.val = nthFromLast.next.val;
        nthFromLast.next = nthFromLast.next.next;

        return head;
    }
}
