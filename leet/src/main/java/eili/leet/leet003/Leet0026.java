package eili.leet.leet003;

/**
 * Problem #:  0026
 * Name:       Remove Duplicates From Sorted Array
 * Tags:       Array
 * BigO:       O(N)
 * Difficulty: Easy
 * Techniques: ?
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example:
 * Given nums = [1,1,2]
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 */
public class Leet0026 {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4}));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int prevVal  = nums[0];
        int writeIdx = 1;
        for (int num : nums) {
            if (num != prevVal) {
                nums[writeIdx++] = num;
            }
            prevVal = num;
        }

        return writeIdx;
    }
}
