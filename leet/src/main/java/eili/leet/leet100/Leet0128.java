package eili.leet.leet100;

import java.util.HashMap;
import java.util.Map;


/**
 * Problem #:  0128
 * Name:       Longest Consecutive Subsequence
 * Tags:       HashMap, Set, Algorithm
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Hard (Medium?)
 * Techniques: Define and Conquer
 * Learnings:  Think about how to define the critical aspect of the problem.  Build an
 *             algorithm around this definition.
 *
 *             For example, in this case what defines a *consecutive sequence*?  Well,
 *             consecutive means one after the other.  So, we can build a solution
 *             around the idea of tracking whether a number has seen the one after it.
 *             Using this tracking, we can count how many numbers make up the total
 *             sequence.
 *
 * Problem Description:
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Input: [0, 0, 1, -1]
 * Output: 3
 */
public class Leet0128 {

    public int longestConsecutive(int[] nums) {

        int max = 0;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, 0);
        }

        for (int num : nums) {
            boolean visited = counts.containsKey(num) && counts.get(num) != 0;
            if (visited) continue;

            int count = 1;
            int candidate = num - 1;
            while (counts.containsKey(candidate)) {
                int countBelow = counts.remove(candidate);
                if (countBelow != 0) {
                    count += countBelow;
                    break;
                } else {
                    count++;
                    candidate--;
                }
            }

            counts.put(num, count);
            max = Math.max(max, count);
        }

        return max;
    }

}
