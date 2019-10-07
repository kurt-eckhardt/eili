package eili.string;

import java.util.*;

public class Huffman {


    public static String compress(char[] chars) {
        Node root = buildHuffmanTree(chars);
        return compress(root, chars);
    }

    public static String compress(Node root, char[] chars) {
        Map<Character, String> charMap = buildCharMap2(root);
        StringBuilder sb = new StringBuilder();
        for (char c: chars) {
            sb.append(charMap.get(c));
        }

        return sb.toString();
    }


    public static String decompress(Node root, String data) {
        StringBuilder sb = new StringBuilder();
        Node runner = root;
        for (char c : data.toCharArray()) {
            if (c == '0') {
                runner = runner.lNode;
            } else {
                runner = runner.rNode;
            }
            if (runner.chr != null) {
                sb.append(runner.chr);
                runner = root;
            }
        }

        return sb.toString();
    }


    /**
     * @param chars
     * @return
     */
    public static Node buildHuffmanTree(char[] chars) {

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : chars) {
            Integer count = freqMap.get(c);
            freqMap.put(c, count == null ? 1 : count+1);
        }

        PriorityQueue<Node> minheap = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            minheap.add(new Node(entry.getValue(), entry.getKey()));
        }

        Node root = null;
        while (!minheap.isEmpty()) {
            if (minheap.size() == 1) {
                root = minheap.remove();
            } else {
                Node lNode = minheap.remove();
                Node rNode = minheap.remove();
                Node inner = new Node(lNode.freq+rNode.freq, lNode, rNode);
                //System.out.println("Making inner node using lNode=["+lNode.chr+"] rNode=["+rNode.chr+"] freq="+(lNode.freq+rNode.freq));
                minheap.add(inner);
            }
        }

        return root;
    }



    /**
     * @param root
     */
    protected static Map<Character, String> buildCharMap(Node root) {
        Node visiting = root;
        Map<Character, String> charMap = new HashMap<>();
        ArrayDeque<Node> toVisit = new ArrayDeque<>();

        while (visiting != null || !toVisit.isEmpty()) {

            while (visiting != null) {
                toVisit.push(visiting);
                if (visiting.lNode != null) {
                    visiting.lNode.pattern = visiting.pattern + "0";
                }
                visiting = visiting.lNode;
            }

            visiting = toVisit.pop();
            if (visiting.chr != null) {
                System.out.println("char=" + visiting.chr + " freq=" + visiting.freq + " path=" + visiting.pattern);
                charMap.put(visiting.chr, visiting.pattern);
            }

            if (visiting.rNode != null) {
                visiting.rNode.pattern = visiting.pattern + "1";
            }

            visiting = visiting.rNode;
        }

        return charMap;
    }


    /**
     * Uses a post-order traversal
     *
     * @param root
     * @return
     */
    protected static Map<Character, String> buildCharMap2(Node root) {

        Map<Character, String> charMap = new HashMap<>();
        ArrayDeque<Node> toVisit = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();

        String path = "";
        toVisit.push(root);
        while (!toVisit.isEmpty()) {

            Node visiting = toVisit.pop();
            visited.add(visiting);

            if (visiting.lNode != null && !visited.contains(visiting.lNode)) {
                toVisit.push(visiting);
                toVisit.push(visiting.lNode);
                path = path + "0";
            } else if (visiting.rNode != null && !visited.contains(visiting.rNode)) {
                toVisit.push(visiting);
                toVisit.push(visiting.rNode);
                path = path + "1";
            } else {
                if (visiting.chr != null) {
                    System.out.println("char=" + visiting.chr + " freq=" + visiting.freq + " path=" + path);
                    charMap.put(visiting.chr, path);
                }
                path = path.substring(0, Math.max(0,path.length()-1));
            }
        }

        return charMap;
    }



    static class Node implements Comparable<Node> {

        int freq;
        Character chr;
        Node lNode;
        Node rNode;
        String pattern = "";

        Node(int freq, Node lNode, Node rNode) {
            this.freq  = freq;
            this.lNode = lNode;
            this.rNode = rNode;
        }

        Node(int freq, Character c) {
            this.freq = freq;
            this.chr  = c;
        }

        @Override
        public int compareTo(Node o) {
            return freq - o.freq;
        }
    }
}
