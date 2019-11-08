package eili.leet.leet008;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #:  0078
 * Name:       All Subsets (aka: power set, all subsequences)
 * Tags:       Math, Combinations, Power Set, Subsequence
 * BigO:       O(2^N)
 * Difficulty: Medium
 * Techniques: ?
 * Learnings:  ?
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Leet0078 {
    public static void main(String[] args) {
        System.out.println(powerset(new int[] {1,2,3}));
    }

    public static List<List<Integer>> powerset(int[] nums) {

        if (nums == null) return new ArrayList<>();

        List<List<Integer>> powerset = new ArrayList<>();

        powerset.add(List.of());
        for (int aNum : nums) {
            List<List<Integer>> moresets = new ArrayList<>();
            for (List<Integer> aSet : powerset) {
                List<Integer> newSet = new ArrayList<>(aSet);
                newSet.add(aNum);
                moresets.add(newSet);
            }
            powerset.addAll(moresets);
        }

        return powerset;
    }

    // This method shows another way to generate a powerset using
    // a bitset to track which character in a string to keep (or not)
    public static void powerset2(String s) {
        int sLength = s.length();
        int numSets = 1 << sLength; // 2^n subsets
        for (int i=0; i < numSets; i++) {
            String subset = "";
            for (int j=0; j < sLength; j++) {
                if (((i >> j) & 0x1) != 0) subset += s.charAt(j);
            }
            System.out.println(subset);
        }
    }
}
