package eili.leet.leet200;

/**
 * Problem #:  0287
 * Name:       Duplicate Number
 * Tags:       Array, Cycle Detection
 * BigO:       O(N) Time, O(1) Space
 * Difficulty: Medium (Hard?)
 * Techniques: Fast/Slow Pointers
 * Learnings:  Floyd's Cycle Finding Algorithm
 *
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 */
public class Leet0287 {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[] {1,1,2,3}));
        System.out.println(findDuplicate(new int[] {1,2,1,3}));
        System.out.println(findDuplicate(new int[] {1,2,3,1}));
    }

    public static int findDuplicate(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        int slowIdx = 0;
        int fastIdx = 0;

        while (true) {
            slowIdx = nums[slowIdx];
            fastIdx = nums[nums[fastIdx]];
            if (slowIdx == fastIdx) {
//                System.out.println("Cycle detected at index=" + slowIdx);
                return findStartOfCycle(nums, slowIdx);
            }
        }
    }

    public static int findStartOfCycle(int[] nums, int slowIdx) {
        int headIdx = 0;
        while (headIdx != slowIdx) {
            headIdx = nums[headIdx];
            slowIdx = nums[slowIdx];
        }

        return slowIdx;
    }

}
