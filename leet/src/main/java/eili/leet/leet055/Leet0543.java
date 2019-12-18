package eili.leet.leet055;

/**
 * Problem #:  543
 * Name:       Diameter of Binary Tree
 * Tags:       Tree
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Easy
 * Techniques: Tree Traversal, Recursion
 * Learnings:  Using a variable outside of the recursion stack makes it easier to maintain the
 *             maximum value.  This way we do not have to return it through the recusion calls
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter
 * of a binary tree is the length of the longest path between any two nodes in a tree. This path
 * may or may not pass through the root.
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 */
public class Leet0543 {

    public static int max = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        diameter(root);
        return max;
    }

    /**
     * Notice how we update "max" which sits outside of the recursion stack calls.
     * This is because the max might be running through the root node and we can't
     * use that to extend up to a parent.
     *
     * @param root
     * @return
     */
    public static int diameter(TreeNode root) {
        if (root == null) return 0;

        int maxLeft  = diameter(root.left);
        int maxRight = diameter(root.right);

        if (root.left != null && root.right != null) max = Math.max(max, maxLeft + maxRight + 2);
        if (root.left != null)  max = Math.max(max, maxLeft+1);
        if (root.right != null) max = Math.max(max, maxRight+1);

        if (root.left != null || root.right != null) {
            return Math.max(maxLeft, maxRight)+1;
        } else {
            return 0;
        }
    }


    /**
     * Instead of returning 0 at root nodes, we return 1.
     * This reduces the number of conditionals and max checks.
     *
     * @param root
     * @return
     */
    public static int optimized(TreeNode root) {
        if (root == null) return 0;

        int maxLeft  = optimized(root.left);
        int maxRight = optimized(root.right);

        max = Math.max(max, maxLeft+maxRight);

        return Math.max(maxLeft, maxRight)+1;
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



