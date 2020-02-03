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
        System.out.println(countSmaller(new int[] {6,4,5,0,2,1,3}));
    }
}
