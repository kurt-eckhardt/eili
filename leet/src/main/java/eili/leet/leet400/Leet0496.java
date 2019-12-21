package eili.leet.leet400;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0496
 * Name:       Next Greater Element I
 * Tags:       Monotonic Stack
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Easy
 * Techniques: Monotonic Stack, Memoization
 * Learnings:
 *
 *
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements
 * are subset of nums2.  Find all the next greater numbers for nums1's elements in the
 * corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right
 * in nums2. If it does not exist, output -1 for this number.
 *
 * Note:
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 */
public class Leet0496 {


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] nextGreater = new int[nums1.length];

        Map<Integer, Integer> nextMap = new HashMap<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                nextMap.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        for (int i=0; i < nums1.length; i++) {
            nextGreater[i] = nextMap.getOrDefault(nums1[i], -1);
        }

        return nextGreater;
    }

}
