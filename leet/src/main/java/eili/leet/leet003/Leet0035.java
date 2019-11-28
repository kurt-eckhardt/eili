package eili.leet.leet003;

/**
 * Problem #:  0035
 * Name:       Search Insert
 * Tags:
 * BigO:       O(Log N) Time,  O(1) Space
 * Difficulty: Easy
 * Techniques: Binary Search
 * Learnings:
 * 1) Trick here is to figure out the return position - in this case, it's max(lo,hi)
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Examples:
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class Leet0035 {

    public int searchInsert(int[] nums, int target) {

        int lo = 0;
        int hi = nums.length-1;

        while (lo <= hi) {
            int midIdx = lo/2 + hi/2 + (lo%2 + hi%2)/2;
            int midVal = nums[midIdx];
            if (midVal == target) {
                return midIdx;
            } else if (midVal > target) {
                hi = midIdx - 1;
            } else {
                lo = midIdx + 1;
            }
        }

        return Math.max(lo,hi);
    }

}
