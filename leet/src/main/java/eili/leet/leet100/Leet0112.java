package eili.leet.leet100;

import java.util.ArrayDeque;

public class Leet0112 {

    public static void main(String[] args) {
//        System.out.println(hasPathSumIter(buildTree(new Integer[]{1,2,1,null,null,4,null,null,5,null,6,7,8}), -27));
//        System.out.println(hasPathSumIter(buildTree(new Integer[]{-1,-4,-1,null,null,-2,null,null,4,null,2,3,-1}), -27));
//        System.out.println(hasPathSumIter(buildTree(new Integer[]{10,1}),11));
//
        TreeNode root = buildTree(new Integer[]{1,-2,-3,1,3,-2,0,-1});
        printTree(root, 8);
        System.out.println();
        System.out.println(hasPathSumIter2(root,3));
    }



    public static boolean hasSum(TreeNode root, int target, int sum) {
        if (root == null) return false;
        sum = sum + root.val;

        if (root.left == null && root.right == null && sum == target) return true;
        return hasSum(root.left, target, sum) ||
               hasSum(root.right, target, sum);
    }


    public static boolean hasPathSumIter(TreeNode root, int target) {

        if (root == null) return false;
        int sum = 0;
        ArrayDeque<TreeNode> nodeStack = new ArrayDeque<>();
        ArrayDeque<TreeNode> pathStack = new ArrayDeque<>();

        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode aNode = nodeStack.peek();
            pathStack.push(aNode);
            sum = sum + aNode.val;
            System.out.println("aNode="+aNode.val + " nodeStack="+nodeStack + " pathStack="+pathStack);
            if (aNode.left == null && aNode.right == null) {
                if (sum == target) return true;
                while (!nodeStack.isEmpty() && nodeStack.peek().equals(pathStack.peek())) {
                    nodeStack.pop();
                    sum = sum - pathStack.pop().val;
                }
            } else {
                if (aNode.right != null) nodeStack.push(aNode.right);
                if (aNode.left  != null) nodeStack.push(aNode.left);
            }
        }

        return false;
    }


    public static boolean hasPathSumIter2(TreeNode root, int target) {

        if (root == null) return false;
        ArrayDeque<Integer>  sumStack  = new ArrayDeque<>();
        ArrayDeque<TreeNode> pathStack = new ArrayDeque<>();

        pathStack.push(root);
        sumStack.push(root.val);
        while (!pathStack.isEmpty()) {
            TreeNode aNode = pathStack.pop();
            int sumToANode = sumStack.pop();
            if (aNode.left == null && aNode.right == null) {
                if (sumToANode == target) return true;
            } else {
                if (aNode.right != null) {
                    sumStack.push(sumToANode+aNode.right.val);
                    pathStack.push(aNode.right);
                }

                if (aNode.left  != null) {
                    sumStack.push(sumToANode+aNode.left.val);
                    pathStack.push(aNode.left);
                }
            }
        }

        return false;
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }

        public String toString() { return val+"";}
    }


    public static TreeNode buildTree(Integer[] nodeVals) {
        TreeNode[] nodes = new TreeNode[nodeVals.length];
        for (int i=0; i < nodeVals.length; i++) {
            nodes[i] = nodeVals[i] == null ? null : new TreeNode(nodeVals[i]);
        }
        for (int i=0; i < nodeVals.length/2; i++) {
            int lNodeIdx = i * 2 + 1;
            int rNodeIdx = i * 2 + 2;
            if (nodes[i] != null) {
                nodes[i].left  = (lNodeIdx < nodeVals.length) ? nodes[lNodeIdx] : null;
                nodes[i].right = (rNodeIdx < nodeVals.length) ? nodes[rNodeIdx] : null;
            }
        }
        return nodes[0];
    }

    public static void printTree(TreeNode root, int total) {

        int nodeCount = 0;
        int lastLevel = -1;
        int indent    = 0;
        int spaces    = 0;
        int maxLevel  = Double.valueOf(Math.log(total)/Math.log(2)).intValue();

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            nodeCount++;
            if (nodeCount > total) break;
            int level  = Double.valueOf(Math.log(nodeCount)/Math.log(2)).intValue();
            TreeNode visiting = queue.removeFirst();
            if (visiting.left  != null) queue.add(visiting.left);
            if (visiting.right != null) queue.add(visiting.right);

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
}