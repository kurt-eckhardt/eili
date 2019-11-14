package eili.scratch;

import java.util.*;

public class KSum {

    public static void main(String[] args) {
        System.out.println(sumK(new int[] {1, 0, -1, -1, 0, -2, 2}, -2, 4));
    }

    public static ArrayList<List<Integer>> sumK(int[] nums, int target, int k) {
        Arrays.sort(nums);
        return new ArrayList<>(sumK(nums, target, k, 0));
    }


    public static ArrayList<List<Integer>> sumK(int[] nums, int target, int k, int startIdx) {
        ArrayList<List<Integer>> solutions = new ArrayList<>();
        if (startIdx >= nums.length) {
            return solutions;
        } else if (k <= 2) {
            return sum2(nums, target, startIdx);
        } else {
            // reduce sumK to sumK-1
            for (int i = startIdx; i < nums.length-k+1; i++) {
                ArrayList<List<Integer>> sums = sumK(nums, target - nums[i], k-1, i+1);
                for (List<Integer> s : sums) {
                    s.add(0, nums[i]);
                }
                //skip duplicated numbers (why does this work?)
                while (i < nums.length-1 && nums[i] == nums[i+1]) {
                    i++;
                }
                solutions.addAll(sums);
            }
            return solutions;
        }
    }


    public static ArrayList<List<Integer>> sum2(int[] nums, int target, int startIdx) {

        ArrayList<List<Integer>> solutions = new ArrayList<>();

        int lo = startIdx;
        int hi = nums.length-1;
        while (lo < hi) {
            int sum2 = nums[lo]+nums[hi];
            if (sum2 == target) {
                solutions.add(new ArrayList<>(Arrays.asList(nums[lo],nums[hi])));
                // skip duplicate numbers
                while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                lo++;
                hi--;
            } else if (sum2 < target) {
                lo++;
            } else {
                hi--;
            }
        }

        return solutions;
    }
}
