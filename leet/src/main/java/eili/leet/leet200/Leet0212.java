package eili.leet.leet200;

import java.util.*;

public class Leet0212 {

    public static void main(String[] args) {
//        char[][] board = new char[][] {{'c','a','t'},{'o','r','a'},{'t','e','p'}};
//        String[] words = new String[] {"cat","are","core","mat"};
//
        char[][] board = new char[][] {{'b','a','a','b','a','b'},{'a','b','a','a','a','a'},{'a','b','a','a','a','b'},{'a','b','a','b','b','a'},{'a','a','b','b','a','b'},{'a','a','b','b','b','a'},{'a','a','b','a','a','b'}};
        String[] words = new String[] {"bbaabaabaaaaabaababaaaaababb","aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa","bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab","bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba","abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa","aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba","abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab","baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa","aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb","baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab","abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab","bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba","bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb","bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa","abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb","babbbaabababbbbbbbbbaabbabaa"};
        System.out.println(findWords(board, words));
    }


    private static Set<String> found = new HashSet<>();

    public static List<String> findWords(char[][] board, String[] words) {
        found.clear();
        Node root = buildTrie(words);
        for (int row=0; row < board.length; row++) {
            for (int col=0; col < board[0].length; col++) {
                findWords(board, root, row, col);
            }
        }


        return new ArrayList<>(found);
    }


    private static void findWords(char[][] board, Node root, int row, int col) {

        if (root.word != null) {
            found.add(root.word);
        }

        if (row < 0 || row >= board.length
         || col < 0 || col >= board[0].length
         || board[row][col]=='.'
         || !root.kids.containsKey(board[row][col])) {
            return;
        }

        char atChar = board[row][col];
        board[row][col] = '.';
        findWords(board, root.kids.get(atChar), row-1, col);
        findWords(board, root.kids.get(atChar), row+1, col);
        findWords(board, root.kids.get(atChar), row, col+1);
        findWords(board, root.kids.get(atChar), row, col-1);
        board[row][col] = atChar;
    }


    private static Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            addWord(word, root);
        }
        return root;
    }

    private static void addWord(String word, Node root) {
        for (char c : word.toCharArray()) {
            if (root.kids.containsKey(c)) {
                root = root.kids.get(c);
            } else {
                Node newKid = new Node();
                root.kids.put(c, newKid);
                root = newKid;
            }
        }

        root.word = word;
    }



    static class Node {
        String word = null;
        Map<Character, Node> kids = new HashMap<>();
    }
}

