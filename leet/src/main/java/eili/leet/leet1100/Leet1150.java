package eili.leet.leet1100;

/**
 * Problem #:  1150
 * Name:       Majority Element in a Sorted Array
 * Tags:       Binary Search,  Mode, Frequency
 * BigO:       O(Log N) Time, O(1) Space
 * Difficulty: Easy
 * Techniques: Binary Search
 */
public class Leet1150 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,4,4,4,4,5};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        } else if (nums.length == 1) {
            return nums[0];
        } else {
            int target = nums[nums.length/2];
            int threshold = nums.length/2+1;
            int loIndex = findFirstIndexOf(target, nums);
            int hiIndex = findLastIndexOf(target, nums);

            return (hiIndex - loIndex + 1 >= threshold) ? target : -1;
        }
    }

    public static int findFirstIndexOf(int target, int[] nums) {
        int lo=0;
        int hi=nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if (nums[mid] == target) {
                if (mid-1 >= 0 && nums[mid-1] == target) {
                    hi = mid-1;
                } else {
                    return lo;
                }
            } else if (nums[mid] < target) {
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }

        return -1;
    }


    public static int findLastIndexOf(int target, int[] nums) {
        int lo=0;
        int hi=nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if (nums[mid] == target) {
                if (mid+1 < nums.length && nums[mid+1] == target) {
                    lo = mid+1;
                } else {
                    return lo;
                }
            } else if (nums[mid] < target) {
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }

        return -1;
    }
}
