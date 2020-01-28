package eili.scratch;

import eili.goog.MaxBinaryDistance.Node;

import java.util.ArrayList;
import java.util.List;

public class TreePath {

    public static void main(String[] args) {
        Node root = new Node('3');
        root.lNode = new Node('1');
        root.lNode.lNode = new Node('0');
        root.lNode.rNode = new Node('2');
        root.rNode = new Node('5');
        root.rNode.lNode = new Node('4');
        root.rNode.rNode = new Node('6');

        List<Node> pathFromNode2ToRoot = rootToNode(root, root.lNode.rNode);
        List<Node> pathFromNode4ToRoot = rootToNode(root, root.rNode.lNode);

        for (Node n : pathFromNode2ToRoot) {
            if (pathFromNode4ToRoot.contains(n)) {
                System.out.println("LowestCommonAncestor=" + n.val);
                break;
            }
        }
    }

    private static List<Node> rootToNode(Node root, Node target) {
        List<Node> path = new ArrayList<>();
        return rootToNode(root, target, path);
    }

    private static List<Node> rootToNode(Node atNode, Node target, List<Node> path) {
        if (atNode == null) {
            return List.of();
        } else if (atNode == target) {
            path.add(atNode);
            return path;
        } else if (!rootToNode(atNode.lNode, target, path).isEmpty()) {
            path.add(atNode);
            return path;
        } else if (!rootToNode(atNode.rNode, target, path).isEmpty()) {
            path.add(atNode);
            return path;
        } else {
            return List.of();
        }
    }
}
