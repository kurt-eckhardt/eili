package eili.leet03;

import eili.leet.ListNode;

import static eili.leet.ListNode.makeList;

/**
 * Problem #:  0021
 * Name:       Merge Two Sorted Lists
 * Tags:       Linked List
 * Difficulty: Easy
 * Techniques: Dummy Pointer
 * Learnings:
 * 1) Again, we needed to use a "dummy pointer" infront of the
 *    actual list so that we could link the previous node to the
 *    smaller of either l1 or l2.
 *
 *
 * Merge two sorted linked lists and return it as a new list.  The new list
 * should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class Leet0021 {
    
    public static void main(String[] args) {
        ListNode merged = merge(makeList(new int[] {1,2,4}), makeList(new int[] {1,3,4}));
        ListNode.printList(merged);
    }


    /**
     * Uses a dummy "curr" node to link the previous node to the
     * smaller of either l1 or l2.  We also use a dummy "head"
     * for ease of returning the final result.
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode merge(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return head.next;
    }


    /**
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeOrig(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = l1.val < l2.val ? l1 : l2;
        ListNode prev = null;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (prev != null) prev.next = l1;
                prev = l1;
                l1 = l1.next;
            } else {
                if (prev != null) prev.next = l2;
                prev = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            prev.next = l2;
        } else {
            prev.next = l1;
        }

        return head;
    }
}
