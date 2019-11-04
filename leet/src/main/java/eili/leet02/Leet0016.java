package eili.leet02;

import java.util.Arrays;

/**
 * Problem #:  0016
 * Name:       Closest 3Sum
 * Tags:       Array
 * Difficulty: Medium
 * Techniques: Two-Pointer, Sliding Window, Greedy
 * Learnings:
 * 1) Solve 3Sums first since it can easily be adapted to produce the closest value
 * 2) Use Math.abs() to get a delta between the desired target and a tested value.
 *
 *
 * Given an array nums of n integers and an integer target, find three integers in nums
 * such that the sum is closest to target. Return the sum of the three integers. You may assume
 * that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class Leet0016 {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        System.out.println(closest3Sum(nums, 1));
    }


    public static int closest3Sum(int[] nums, int target) {

        Arrays.sort(nums);

        int bestDelta = Integer.MAX_VALUE;
        int closest   = Integer.MAX_VALUE;

        for (int i=0; i < nums.length-2; i++) {
            int[] closest2Sum = closest2Sum(nums, target-nums[i], i+1);
            int aSum3 = nums[i] + closest2Sum[0] + closest2Sum[1];
            int delta = Math.abs(target-aSum3);
            if (delta < bestDelta) {
                bestDelta = delta;
                closest   = nums[i] + closest2Sum[0] + closest2Sum[1];
            }
        }

        return closest;
    }


    public static int[] closest2Sum(int[] nums, int target, int lo) {

        int bestDelta = Integer.MAX_VALUE;
        int bestNum0  = Integer.MAX_VALUE;
        int bestNum1  = Integer.MAX_VALUE;

        int hi = nums.length-1;
        while (lo < hi) {
            int sum = nums[lo]+nums[hi];
            int delta = Math.abs(target - sum);
            if (delta < bestDelta) {
                bestDelta = delta;
                bestNum0 = nums[lo];
                bestNum1 = nums[hi];
            }

            if (sum > target) {
                hi--;
            } else {
                lo++;
            }
        }

        return new int[] { bestNum0, bestNum1 };
    }

}
