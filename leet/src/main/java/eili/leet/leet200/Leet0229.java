package eili.leet.leet200;

import java.util.*;

/**
 * Problem #:  0229
 * Name:       Majority Element II
 * Tags:       Boyer-Moyer, Voting, Mode, Frequency
 * BigO:       O(N) Time, O(1) Space
 * Difficulty: Medium (Hard!)
 * Techniques: Booth-Moyer Algorithm
 * Learnings:
 * 1) See: https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
 *    Without knowledge of Boyer-Moyer, this is pretty difficult to figure out.
 *    The basic idea is the major element (i.e. the mode) has to appear in at
 *    least half the array elements.  We can find this by counting the number of
 *    times we've seen the last element in the array.
 *
 * 2) Normally, the majority element algorithm looks for n/2+1 so a single counter works.
 *    In this case, we can have 0,1,or 2 major elements.  So, we need to keep two counters.
 *
 * Problem Description:
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class Leet0229 {

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,3,4,5,6};
        System.out.println(majorityElement(nums));
    }

    public static List<Integer> majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) return List.of();

        List<Integer> candidates = majorityElements(nums);
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (candidates.contains(num)) {
                counts.put(num, counts.getOrDefault(num,0)+1);
            }
        }

        int threshold = nums.length/3 + 1;
        List<Integer> majority = new ArrayList<>();
        for (int candidate : counts.keySet()) {
            if (counts.get(candidate) >= threshold) {
                majority.add(candidate);
            }
        }

        return majority;
    }


    public static List<Integer> majorityElements(int[] nums) {

        int n1 = 0;
        int n2 = 0;
        int c1 = 0;
        int c2 = 0;

        for (int num : nums) {
            if (num == n1) {
                c1++;
            } else if (num == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = num;
                c1 = 1;
            } else if (c2 == 0) {
                n2 = num;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        System.out.println("Candidates: ["+n1 + ", count="+c1 + "]  ["+n2 + ", count="+c2+"]");
        return List.of(n1,n2);
    }
}
