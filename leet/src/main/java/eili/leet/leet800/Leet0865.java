package eili.leet.leet800;

/**
 * Problem #:  0865
 * Name:       Smallest Subtree with all the Deepest Nodes
 * Tags:       Tree Traversal
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Medium
 * Techniques: DFS
 *
 * Problem Description:
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is that node, plus the set of all descendants of that node.
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 */
public class Leet0865 {

    public int maxDepth = -1;
    public TreeNode deepest;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        deepestSubtree(root, 0);
        return deepest;
    }

    /**
     * Set root to the subtree root only when left and right
     * children have the same value AND that value >= the current
     * deepest.  Handle edge cases when the root is the deepest
     * node and it has no children.
     *
     * @param root
     * @param depth
     * @return
     */
    public int deepestSubtree(TreeNode root, int depth) {
        if (root == null) return -1;
        int maxLDepth = deepestSubtree(root.left,  depth + 1);
        int maxRDepth = deepestSubtree(root.right, depth + 1);
        int maxRootLR = Math.max(Math.max(maxLDepth, maxRDepth), depth);

        if (maxLDepth == maxRDepth && maxRootLR >= maxDepth) {
            deepest = root;
        }

        maxDepth = Math.max(maxDepth, maxRootLR);
        return maxRootLR;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

//    public int deepestSubtree_orig(TreeNode root, int depth) {
//        if (root == null) return 0;
//        int maxLDepth = deepestSubtree(root.left,  depth + 1);
//        int maxRDepth = deepestSubtree(root.right, depth + 1);
//        int maxRootLR = Math.max(Math.max(maxLDepth, maxRDepth), depth);
//
//        if (maxLDepth == maxRDepth && maxLDepth >= maxDepth) {
//            deepest  = root;
//            maxDepth = maxRootLR;
//        } else {
//            if (depth > maxDepth) {
//                deepest  = root;
//                maxDepth = depth;
//            }
//
//            if (maxLDepth > maxDepth) {
//                deepest  = root.left;
//                maxDepth = maxLDepth;
//            }
//
//            if (maxRDepth > maxDepth) {
//                deepest  = root.right;
//                maxDepth = maxRDepth;
//            }
//        }
//
//        return maxRootLR;
//    }
}
