package eili.leet03;

import eili.leet.ListNode;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem #:  0023
 * Name:       Merge K Sorted Lists
 * Tags:       Linked List, Heap
 * BigO:       O(N*Log(K))
 * Difficulty: Hard
 * Techniques: Dummy Pointer
 * Learnings:
 * 1) No need for a container class to store the ListNode that was returned
 *    from the minHeap because once we have access to the ListNode we can
 *    use it to find the next item in the list that needs to be enqueued.
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class Leet0023 {

    public static void main(String[] args) {
        ListNode l1 = ListNode.makeList(new int[] {1,4,5});
        ListNode l2 = ListNode.makeList(new int[] {1,3,4});
        ListNode l3 = ListNode.makeList(new int[] {2,6});
        ListNode l4 = mergeKLists(new ListNode[] { l1, l2, l3 });
        ListNode.printList(l4);
    }

    /**
     * O(N*log(K))
     * Each add/remove from the heap costs Log K where K = number of lists
     * We do this N times where n = total number of items in all lists
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode curr = head;

        ListNodeComparator lnComparator = new ListNodeComparator();
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lnComparator);
        minHeap.addAll(Stream.of(lists).filter(Objects::nonNull).collect(Collectors.toList()));

        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.remove();
            curr.next = min;
            curr = min;
            if (min.next != null) {
                minHeap.add(min.next);
            }
        }

        return head.next;
    }


    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode l1, ListNode l2) {
            if (l1.val < l2.val) {
                return -1;
            } else if (l1.val > l2.val) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}

