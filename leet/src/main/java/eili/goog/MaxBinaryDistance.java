package eili.goog;

import java.util.Arrays;

public class MaxBinaryDistance {

    public static void main(String[] args) {
        String[] bins = new String[] {"1011110","1011000","01","1","0","0000100"};

        Node root = new Node('x');
        for (String b : bins) {
            Node runner = root;
            char[] chars = b.toCharArray();
            for (char c : chars) {
                while (true) {
                    if (c == '0') {
                        if (runner.lNode == null) {
                            runner.lNode = new Node(c);
                        }
                        runner = runner.lNode;
                        break;
                    } else {
                        if (runner.rNode == null) {
                            runner.rNode = new Node(c);
                        }
                        runner = runner.rNode;
                        break;
                    }
                }
            }
        }

        Node runner = root;
        String min = "";
        while (runner != null) {
//            System.out.println("Runner.val="+runner.val + " lNode="+runner.lNode + " rNode="+runner.rNode);
            if (runner != root) min = min + runner.val;
            if (runner.lNode != null) runner = runner.lNode; else runner = runner.rNode;
        }

        runner = root;
        String max = "";
        while (runner != null) {
            if (runner != root) max = max + runner.val;
            if (runner.rNode != null) runner = runner.rNode; else runner = runner.lNode;
        }

        // TODO - strip off prefix
        System.out.println("min="+min + " max="+max + " dist=" + (min.length() + max.length()));
    }


    public static class Node {
        char val;
        Node lNode;
        Node rNode;

        public Node(char val) {
            this.val = val;
        }
    }
}
