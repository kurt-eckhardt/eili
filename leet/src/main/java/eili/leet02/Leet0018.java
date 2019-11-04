package eili.leet02;

import java.util.*;

/**
 * Problem #:  0018
 * Name:       4Sum
 * Tags:       Math, Subset, Combinations, Array, 2Sum
 * Difficulty: Medium
 * Techniques: Memoization, Sliding Window, Two-Pointer
 * Learnings:  ?
 *
 *
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such
 * that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class Leet0018 {

    // [[-2, -1, -1, 2], [-1, -1, 0, 0], [-2, -1, 0, 1]]
    public static void main(String[] args) {
        System.out.println(sum42(new int[] {1, 0, -1, -1, 0, -2, 2}, -2));

    }

    public static List<List<Integer>> sum42(int[] nums, int target) {
        Arrays.sort(nums);
        HashSet<List<Integer>> solutions = new HashSet<>();
        for (int i=0; i < nums.length; i++) {
            for (int j=i+1; j < nums.length; j++) {
                int needSum2 = target-(nums[i]+nums[j]);
                int lo = 0;
                int hi = nums.length-1;
                while (lo < hi) {
                    int sum2 = nums[lo]+nums[hi];
                    if (lo == i || lo == j || sum2 < needSum2) {
                        lo++;
                    } else if (hi == i || hi == j || sum2 > needSum2) {
                        hi--;
                    } else if (sum2 == needSum2) {
                        List<Integer> solution = Arrays.asList(nums[i],nums[j],nums[lo],nums[hi]);
                        Collections.sort(solution);
                        solutions.add(solution);
                        lo++;
                    } else {
                        lo++;
                    }
                }
            }
        }

        return new ArrayList<>(solutions);
    }


    public static HashSet<List<Integer>> sum2(int[] nums, int target, int omitIdx1, int omitIdx2) {

        HashSet<List<Integer>> solutions = new HashSet<>();

        int lo = 0;
        int hi = nums.length-1;
        while (lo < hi) {
            int sum2 = nums[lo]+nums[hi];
            if (lo == omitIdx1 || lo == omitIdx2 || sum2 < target) {
                lo++;
            } else if (hi == omitIdx1 || hi == omitIdx2 || sum2 > target) {
                hi--;
            } else if (sum2 == target) {
                List<Integer> solution = Arrays.asList(nums[lo],nums[hi]);
                Collections.sort(solution);
                solutions.add(solution);
                lo++;
            } else {
                lo++;
            }
        }

        return solutions;
    }


    //  [-1,  0, 0, 1],
    //  [-2, -1, 1, 2],
    //  [-2,  0, 0, 2]
    public static List<List<Integer>> sum4(int[] nums, int target) {
        Arrays.sort(nums);
        HashSet<List<Integer>> solutions = new HashSet<>();
        for (int i=0; i < nums.length; i++) {
            for (int j=i+1; j < nums.length; j++) {
                for (List<Integer> a2sum : sum2(nums, target-(nums[i]+nums[j]), i, j)) {
                    List<Integer> solution = Arrays.asList(nums[i], nums[j], a2sum.get(0), a2sum.get(1));
                    Collections.sort(solution);
                    solutions.add(solution);
                }
            }
        }

        return new ArrayList<>(solutions);
    }
}
