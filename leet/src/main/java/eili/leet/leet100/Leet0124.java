package eili.leet.leet100;

/**
 * Problem #:  124
 * Name:       Binary Tree Maximum Path Sum
 * Tags:       Tree, Classic
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Hard (Medium?)
 * Techniques: Recursion, Tree Traversal
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class Leet0124 {

    static int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxSum(root);
        return max;
    }

    public static int maxSum(TreeNode root) {

        if (root == null) return 0;

        int lVal = maxSum(root.left);
        int rVal = maxSum(root.right);

        // maximum using left or right child or none
        int rootMax = root.val; // start/end here
        rootMax = Math.max(rootMax, lVal+root.val);
        rootMax = Math.max(rootMax, rVal+root.val);

        // maximum passing through root to left & right children
        max = Math.max(max, lVal+root.val+rVal);
        max = Math.max(max, rootMax);

        return rootMax;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
