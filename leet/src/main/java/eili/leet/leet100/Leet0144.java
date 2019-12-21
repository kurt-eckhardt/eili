package eili.leet.leet100;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem #:  0144
 * Name:       Next Greater Element II
 * Tags:       Tree Traversal
 * BigO:       Time O(N), O(Log N)
 * Difficulty: Medium (Easy?)
 * Techniques: Traversal
 * Learnings:
 *
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Leet0144 {

    public static void main(String[] args) {
        Node root  = new Node(1);
        root.lNode = new Node(2);
        root.rNode = new Node(3);
        root.lNode.lNode = new Node(4);
        root.lNode.rNode = new Node(5);
        root.rNode.lNode = new Node(6);
        root.rNode.rNode = new Node(7);

        System.out.println(traversePre(root));
    }

    /**
     * PreOrder - Visit left nodes, then parent, then right.
     *
     *       1
     *   2      3
     * 4   5  6   7
     *
     * @param root
     * @return
     */
    public static List<Integer> traversePre(Node root) {
        ArrayList<Integer> vals  = new ArrayList<>();
        ArrayDeque<Node> toVisit = new ArrayDeque<>();

        if (root == null) return vals;
        toVisit.push(root);
        while (!toVisit.isEmpty()) {
            Node visiting = toVisit.pop();
            vals.add(visiting.val);
            if (visiting.rNode != null) {
                toVisit.push(visiting.rNode);
            }

            if (visiting.lNode != null) {
                toVisit.push(visiting.lNode);
            }
        }

        return vals;
    }


    public static class Node {
        int val;
        Node lNode;
        Node rNode;
        public Node(int val) {
            this.val = val;
        }
    }

}
