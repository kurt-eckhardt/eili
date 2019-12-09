package eili.leet.leet002;

/**
 * Problem #:  0011
 * Name:       Container with Most Water
 * Tags:
 * Difficulty: Medium
 * Techniques: Two-Pointers, Sliding Window, Greedy? (it updates best so far, but will always find the optimal solution)
 * Learnings:
 * 1) Tried a Break-It-Down approach, but it did not work.  I learned this is actually a Two-Pointer solution.
 *
 *
 *
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated n time, b 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * Learnings:
 * 1) If you can't figure it out after an hour, lookup the approach.
 * 2) If you aren't familiar with the approach, learn about the technique before writing code.
 * 3) "moving the larger one cannot give an increase in area"
 * 4) We starts with the widest container, l = 0 and r = n - 1. Let's say the left one is shorter: h[l] < h[r]. Then, this is already the largest container the left one can possibly form. There's no need to consider it again. Therefore, we just throw it away and start again with l = 1 and r = n -1.
 */
public class Leet0011 {

    public static void main(String[] args) {
        System.out.println("maxArea[1,8,6,2,5,4,8,3,7]="+solution(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println("maxArea[1,2,4,3]="+solution(new int[] {1,2,4,3}));
        System.out.println("maxArea[2,3,10,5,7,8,9]="+solution(new int[] {2,3,10,5,7,8,9}));
        System.out.println("maxArea[3,2,1,3]="+solution(new int[] {3,2,1,3}));
        System.out.println("maxArea[1,3,2,5,25,24,5]="+solution(new int[] {1,3,2,5,25,24,5}));
    }

    public static int solution(int[] height) {
        int lIdx = 0;
        int rIdx = height.length-1;
        int maxSoFar = Math.min(height[0], height[rIdx]) * rIdx;
        while (lIdx < rIdx) {
            int areaMoveLIdx = Math.min(height[lIdx+1], height[rIdx]) * (rIdx-lIdx-1);
            int areaMoveRIdx = Math.min(height[lIdx], height[rIdx-1]) * (rIdx-1-lIdx);
            maxSoFar = Math.max(maxSoFar, areaMoveLIdx);
            maxSoFar = Math.max(maxSoFar, areaMoveRIdx);
            if (height[lIdx] <= height[rIdx]) {
                lIdx++;
            } else {
                rIdx--;
            }
        }

        return maxSoFar;
    }

//    public static int bad_solution(int[] height) {
//        int maxLIdx = 0;
//        int maxRIdx = 1;
//        int maxSoFar = Math.min(height[0], height[1]);
//        for (int i = 2; i < height.length; i++) {
//            int areaUsingLIdx = Math.min(height[maxLIdx], height[i]) * (i - maxLIdx);
//            int areaUsingRIdx = Math.min(height[maxRIdx], height[i]) * (i - maxRIdx);
//            if (areaUsingLIdx > maxSoFar || areaUsingRIdx > maxSoFar) {
//                if (areaUsingLIdx > areaUsingRIdx) {
//                    maxSoFar = areaUsingLIdx;
//                    maxRIdx  = i;
//                } else if (areaUsingLIdx < areaUsingRIdx) {
//                    maxSoFar = areaUsingRIdx;
//                    maxLIdx  = maxRIdx;
//                    maxRIdx  = i;
//                } else if (height[maxLIdx] >= height[maxRIdx]) {
//                    maxSoFar = areaUsingLIdx;
//                    maxRIdx  = i;
//                } else {
//                    maxSoFar = areaUsingRIdx;
//                    maxLIdx  = maxRIdx;
//                    maxRIdx  = i;
//                }
//            }
//        }
//
//        return maxSoFar;
//    }
}
