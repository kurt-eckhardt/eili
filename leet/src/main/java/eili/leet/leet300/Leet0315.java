package eili.leet.leet300;

import java.util.ArrayList;
import java.util.List;


/**
 * Problem #:  0315
 * Name:       Count of Smaller Numbers After Self
 * Tags:       Binary Tree
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Hard (Medium?)
 * Techniques: Binary Tree Traversal
 * Learnings:  Had to modify the original solution to keep track of how many nodes on the left we are greater than
 *
 * Problem Description:
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements
 * to the right of nums[i].
 *
 * Example:
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class Leet0315 {


    static int swaps;
    static void countSmallerMerge(int[] nums) {
        mergesort(nums, 0, nums.length-1);
    }

    static void mergesort(int[] nums, int lo, int hi) {

        if (lo >= hi) return;
        int mid = (lo + hi) >>> 1;
        mergesort(nums, lo, mid);
        mergesort(nums, mid+1, hi);

        System.out.println("About to merge lo="+lo + " mid=" + mid + " hi="+hi);
        for (int i=lo; i <= mid; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        for (int i=mid+1; i <= hi; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();


        int loRun = lo;
        int hiRun = mid+1;
        while (loRun <= mid && hiRun <= hi) {
            System.out.println("loRun="+loRun + " hiRun="+hiRun);
            if (nums[loRun] < nums[hiRun]) {
                loRun++;
            } else {
                int temp = nums[hiRun];
                for (int i=hiRun; i > loRun; i--) {
                    nums[i] = nums[i-1];
                }
                nums[loRun] = temp;
                loRun++;
                hiRun++;
                mid++;
            }
        }

        System.out.print("Done merging lo="+lo + " mid=" + mid + " hi="+hi + " result=");
        for (int i=lo; i <= hi; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }


    static List<Integer> countSmaller(int[] nums) {

        if (nums == null || nums.length == 0) return List.of();

        TreeNode root = new TreeNode(nums[nums.length-1]);
        nums[nums.length-1]=0;
        for (int i=nums.length-2; i >= 0; i--) {

            int count = 0;
            TreeNode prev = null;
            TreeNode runr = root;
            while (runr != null) {
                prev = runr;
                if (nums[i] > runr.val) {
                    count += runr.leftCount + 1;
                    runr = runr.right;
                } else {
                    runr.leftCount++;
                    runr = runr.left;
                }
            }

            if (nums[i] > prev.val) {
                prev.right = new TreeNode(nums[i]);
            } else {
                prev.left  = new TreeNode(nums[i]);
            }

            nums[i] = count;
        }

        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) list.add(num);
        return list;
    }

    static class TreeNode {
        int val;
        int leftCount;
        TreeNode right;
        TreeNode left;

        TreeNode(int val) {
            this.val = val;
            this.leftCount = 0;
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[] {6,4,5,0,2,1,3};
        int[] nums = new int[] {6,5,4,3,2,1,0};
//        System.out.println(countSmaller(new int[] {6,4,5,0,2,1,3}));
        countSmallerMerge(nums);
        for (int num : nums) System.out.println(num);
    }
}
