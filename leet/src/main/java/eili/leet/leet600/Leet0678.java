package eili.leet.leet600;


import java.util.ArrayDeque;

/**
 * Problem #:  687
 * Name:       Longest Univalue Path
 * Tags:       Tree
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Easy (Medium?, Hard?)
 * Techniques: Recursion, Post-Order Traversal
 * Learnings:  This was a really tough problem.  Even after breaking the problem
 *             down into three use cases (don't use kids, use one child, use both),
 *             the edge cases were very tricky to get right.  It would have been
 *             easier to complete this if I had a better example to start with that
 *             showed me why (where) my original algorithm failed.  I had to code
 *             the showTree algorithm just to find out where the problem was!
 *
 * Example:
 *                                7
 *                7                               7
 *        7               7               7               3
 *    6       7       1       1       1       7       2       2
 *  2   2   2   2   2   2   2   2   2   2   2   7   2   2   2   1
 * 1 5 5 5 0 0 5 5 4 1 1 2 2 8 8 4 3 3 3 0 0 9 7 8 6 2 2 4 4 3 3 3
 *
 * Longest Path = 8
 *
 * Given a binary tree, find the length of the longest path where each node in the path has
 * the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 */
public class Leet0678 {

    public static int longestUnivaluePath(Node root) {
        Result result = longestPath(root);
        return result.maxVal();
    }

    public static Result longestPath(Node root) {

        if (root == null) return new Result();

        Result lResult = longestPath(root.lNode);
        Result rResult = longestPath(root.rNode);

        Result result = new Result();

        if (root.lNode != null && root.rNode != null &&
            root.lNode.val == root.rNode.val && root.lNode.val == root.val) {
            result.bothKidsSameVal = lResult.oneChildSameVal + rResult.oneChildSameVal + 2;
            result.oneChildSameVal = Math.max(lResult.oneChildSameVal,rResult.oneChildSameVal) + 1;
        } else if (root.lNode != null && root.lNode.val == root.val) {
            result.oneChildSameVal = lResult.oneChildSameVal+1;
        } else if (root.rNode != null && root.rNode.val == root.val) {
            result.oneChildSameVal = rResult.oneChildSameVal+1;
        }

        result.zeroKidsSameVal = Math.max(lResult.maxVal(), rResult.maxVal());
        return result;
    }


    public static class Result {
        int bothKidsSameVal = 0;
        int oneChildSameVal = 0;
        int zeroKidsSameVal = 0;

        public int maxVal() {
            return Math.max(zeroKidsSameVal, Math.max(oneChildSameVal,bothKidsSameVal));
        }
    }

    public static class Node {
        int val;
        Node lNode;
        Node rNode;
        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] nodeVals1 = new int[] {7,7,7,7,7,7,3,6,7,1,1,1,7,2,2,2,2,2,2,2,2,2,2,2,2,2,7,2,2,2,1,1,5,5,5,0,0,5,5,4,1,1,2,2,8,8,4,3,3,3,0,0,9,7,8,6,2,2,4,4,3,3,3};
        Node root = buildTree(nodeVals1);
        showTreeBFS(root, nodeVals1.length);
        System.out.println("\n");
        System.out.println("Longest Path="+longestUnivaluePath(root));
    }


    public static void showTreeBFS(Node root, int total) {

        int nodeCount = 0;
        int lastLevel = -1;
        int indent    = 0;
        int spaces    = 0;
        int maxLevel  = Double.valueOf(Math.log(total)/Math.log(2)).intValue();

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            nodeCount++;
            if (nodeCount > total) break;
            int level  = Double.valueOf(Math.log(nodeCount)/Math.log(2)).intValue();
            Node visiting = queue.removeFirst();
            if (visiting.lNode != null) queue.add(visiting.lNode);
            if (visiting.rNode != null) queue.add(visiting.rNode);
//            System.out.println("nodeCount="+nodeCount + " level="+level + " val="+visiting.val);

            if (level != lastLevel) {
                lastLevel = level;
                indent = Double.valueOf(Math.pow(2,maxLevel-level)).intValue()-1;
                spaces = indent * 2 + 1;
                System.out.println();
                for (int i=0; i < indent; i++) System.out.print(" ");
            }
            System.out.print(visiting.val);
            for (int i=0; i < spaces; i++) System.out.print(" ");
        }
    }

    public static Node buildTree(int[] nodeVals) {
        Node[] nodes = new Node[nodeVals.length];
        for (int i=0; i < nodeVals.length; i++) {
            nodes[i] = new Node(nodeVals[i]);
        }
        for (int i=0; i < nodeVals.length/2; i++) {
            int lNodeIdx = i * 2 + 1;
            int rNodeIdx = i * 2 + 2;
            nodes[i].lNode = (lNodeIdx < nodeVals.length) ? nodes[lNodeIdx] : null;
            nodes[i].rNode = (rNodeIdx < nodeVals.length) ? nodes[rNodeIdx] : null;
        }
        return nodes[0];
    }

}
