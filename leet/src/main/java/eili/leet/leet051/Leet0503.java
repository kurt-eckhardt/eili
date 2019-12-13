package eili.leet.leet051;

import java.util.ArrayDeque;

/**
 * Problem #:  0503
 * Name:       Next Greater Element II
 * Tags:       Monotonic Stack
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Medium
 * Techniques: Monotonic Stack, Memoization
 * Learnings:
 *
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element. The Next Greater Number of a number x is the
 * first greater number to its traversing-order next in the array, which means you could search
 * circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Note: The length of given array won't exceed 10000.
 *
 * Example:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class Leet0503 {

    public static int[] nextGreaterElements(int[] nums) {

        int[] nextGreater = new int[nums.length];

        int maxIdx = 0;
        int maxVal = Integer.MIN_VALUE;
        for (int i=0; i < nums.length; i++) {
            if (nums[i] >= maxVal) {
                maxIdx = i;
                maxVal = nums[i];
            }
        }
//        System.out.println("maxVal="+maxVal + " maxIdx="+maxIdx);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i < nums.length; i++) {
            int rotIdx = (i+maxIdx+1) % nums.length;
//            System.out.println("i="+i + " rotIdx="+rotIdx + " val[rotIdx]="+ nums[rotIdx]+ " stack="+stack);
            while (!stack.isEmpty() && nums[rotIdx] > nums[stack.peek()]) {
                nextGreater[stack.pop()] = nums[rotIdx];
            }
            stack.push(rotIdx);
        }

        while (!stack.isEmpty()) {
            nextGreater[stack.pop()] = -1;
        }

        return nextGreater;
    }

    public static void main(String[] args) {
        for (int n : nextGreaterElements(new int[] {1,2,1})) {
            System.out.print(n + " ");
        }
    }
}
