package eili.leet.leet200;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem #:  0295
 * Name:       Find Median from Data Stream
 * Tags:       Datastructure, Heap, Median
 * BigO:       Time O(Log N), O(N)
 * Difficulty: Hard
 * Techniques: Heap
 *
 * Learnings:
 * Heaps can be used to find the middle (median) in a list of numbers.
 *
 * Approach:
 * Use two heaps.
 * The first heap (loHalf) contains the smaller half of numbers.
 * The second heap (hiHalf) contains the larger half of the numbers.
 * Importantly, the loHalf is also a maxHeap so that the top always has the biggest
 * of the small half of numbers.  Similarly, the hiHalf is a minHeap so that it
 * always has the smallest of the large half of numbers.
 *
 * Problem Description:
 * Median is the middle value in an ordered integer list.  If the size of the list is even, there is no
 * middle value.  So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 */
public class Leet0295 {

    static class MedianFinder {

        int size = 0;
        PriorityQueue<Integer> loHalf;
        PriorityQueue<Integer> hiHalf;

        /** initialize your data structure here. */
        public MedianFinder() {
            size = 0;
            loHalf = new PriorityQueue<>(Comparator.reverseOrder());
            hiHalf = new PriorityQueue<>();
        }

        public void addNum(int num) {

            if (size % 2 == 0) {
                if (!hiHalf.isEmpty() && num > hiHalf.peek()) {
                    loHalf.add(hiHalf.remove());
                    hiHalf.add(num);
                } else {
                    loHalf.add(num);
                }
            } else {
                if (!loHalf.isEmpty() && num < loHalf.peek()) {
                    hiHalf.add(loHalf.remove());
                    loHalf.add(num);
                } else {
                    hiHalf.add(num);
                }
            }

            size++;
        }

        public double findMedian() {
            if (size == 0) {
                return -1d;
            } else if (size % 2 == 0) {
                return (double)(loHalf.peek() + hiHalf.peek()) / 2;
            } else {
                return (double)(loHalf.peek());
            }
        }
    }
}
