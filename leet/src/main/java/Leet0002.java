/**
 * Problem #:  0002
 * Name:       Add Two Numbers
 * Tags:       Linked-List
 * Difficulty: Medium
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
 * reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class Leet0002 {

    /**
     * Gotchas:
     * 1) Lists may have different length
     * 2) Lists may be empty but carry may have a value
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode solution(ListNode l1, ListNode l2) {

        Integer carry = 0;
        ListNode head = null;
        ListNode prev = null;

        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;

            // Calculate sum and carry bits
            int tot = l1Val + l2Val + carry;
            int rem = tot % 10;
            carry   = tot / 10;

            // Update solution pointers
            ListNode tail = new ListNode(rem);
            if (head == null) head = tail;
            if (prev != null) prev.next = tail;
            prev = tail;

            // Advance lists to next digits
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return head;
    }

    // Given by LeetCode
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
