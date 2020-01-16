package eili.leet.leet100;

/**
 * Problem #:  0169
 * Name:       Majority Element
 * Tags:       Boyer-Moore, Voting, Mode, Frequency
 * BigO:       O(N) Time, O(1) Space
 * Difficulty: Easy (Hard?)
 * Techniques: Booth-Moore Algorithm
 * Learnings:
 * 1) Boyer-Moore
 * 2) It is impossible to discard more majority elements than minority elements
 *
 * Problem Description:
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class Leet0169 {

    public int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) return -1;

        int count     = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return (count > nums.length/2) ? candidate : -1;
    }
}
