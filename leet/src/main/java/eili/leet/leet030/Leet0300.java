package eili.leet.leet030;

/**
 * Problem #:  0300
 * Name:       Longest Increasing Subsequence
 * Tags:
 * BigO:       O(N^2) Time, O(N) Space
 * Difficulty: Medium
 * Techniques: Dynamic Programming, Bottom Up
 * Learnings:
 *
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n^2) complexity.
 *
 * There is also an O(N Log N) solution.
 */
public class Leet0300 {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {9,10,2,5,4,2,1,9,7}));
        System.out.println(lengthOfLIS(new int[] {10,9,2,3,4,5,1,9,7}));
    }

    public static int lengthOfLIS(int[] nums) {

        int max = 0;
        int[] longestAtIndex = new int[nums.length];
        for (int i=0; i < nums.length; i++) {
            longestAtIndex[i] = 1;
            for (int j=i-1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    longestAtIndex[i] = Math.max(longestAtIndex[i], longestAtIndex[j]+1);
                }
            }

            max = Math.max(max, longestAtIndex[i]);
        }

        return max;
    }

}
