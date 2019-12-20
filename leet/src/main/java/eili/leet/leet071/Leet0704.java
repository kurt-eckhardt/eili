package eili.leet.leet071;

/**
 * Problem #:  704
 * Name:       Binary Search
 * Tags:
 * BigO:       Time O(Log N), Space O(1)
 * Difficulty: Easy
 * Techniques: Binary Search
 * Learnings:  Remember lo <= hi (not strictly less than lo < hi)
 *
 * Given a sorted (in ascending order) integer array nums of n elements and a target value,
 * write a function to search target in nums. If target exists, then return its index,
 * otherwise return -1.
 */
public class Leet0704 {

    public int search(int[] nums, int target) {

        if (nums == null) return -1;

        int lo = 0;
        int hi = nums.length-1;

        while (lo <= hi) {
            int mid = (lo/2)+(hi/2)+((lo%2+hi%2)/2);
            int val = nums[mid];
            if (val == target) {
                return mid;
            } else if (val > target) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }

        return -1;
    }
}
