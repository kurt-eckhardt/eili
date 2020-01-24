package eili.leet.leet800;

import java.util.*;

/**
 * Problem #:  0863
 * Name:       All Nodes distance K in Binary Tree
 * Tags:       Tree Traversal, BFS
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Medium
 * Techniques: BFS Traversal
 * Learnings:
 * 1) Trying to traverse "up the tree" is hard.  Use an auxilary data structure (Map, Matrix) that
 *    allows us to lookup parent nodes in O(1) time.
 *
 * We are given a binary tree (with root node root), a start node, and an integer value 'k'.
 * Return a list of the values of all nodes that have a distance K from the target node.
 * The answer can be returned in any order.
 */
public class Leet0863 {

    public static List<Integer> distanceK(TreeNode root, TreeNode start, int k) {

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Build reverse map so we can traverse back to parent nodes
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode atNode = queue.pop();
            if (atNode.left != null) {
                queue.add(atNode.left);
                parentMap.put(atNode.left, atNode);
            }

            if (atNode.right != null) {
                queue.add(atNode.right);
                parentMap.put(atNode.right, atNode);
            }
        }

        // Now run BFS starting at start
        List<Integer> results = new ArrayList<>();
        bfs(start, parentMap, k, new HashSet<>(), results);
        return results;
    }


    private static void bfs(TreeNode start, Map<TreeNode,TreeNode> parentMap, int k, Set<TreeNode> visited, List<Integer> results) {
        if (start == null || visited.contains(start) || k < 0) return;
        visited.add(start);

        if (k == 0) {
            results.add(start.val);
        } else {
            bfs(start.left,  parentMap, k-1, visited, results);
            bfs(start.right, parentMap, k-1, visited, results);
            bfs(parentMap.get(start), parentMap, k-1, visited, results);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
