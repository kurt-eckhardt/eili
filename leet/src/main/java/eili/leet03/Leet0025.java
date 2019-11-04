package eili.leet03;

import eili.leet.ListNode;

import static eili.leet.ListNode.makeList;
import static eili.leet.ListNode.printList;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of
 * nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class Leet0025 {

    public static void main(String[] args) {
        printList(reverseK(makeList(new int[] {1,2,3,4,5}),4));
        printList(reverseK(makeList(new int[] {1}),2));
    }

    public static ListNode reverseK(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        ListNode prev  = dummy;
        ListNode curr  = head;
        while (curr != null) {
            int numToTake = k;
            ListNode tail = curr;
            while (tail != null && numToTake > 0) {
                tail = tail.next;
                numToTake--;
            }

            if (numToTake == 0) {
                prev.next = reverse(curr, tail);
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = curr;
                break;
            }
        }

        return dummy.next;
    }


    public static ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev = tail;
        ListNode curr = head;
        while (curr != null && curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
