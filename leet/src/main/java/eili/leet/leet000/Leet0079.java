package eili.leet.leet000;

/**
 * Problem #:  79
 * Name:       Word Search
 * Tags:       Matrix
 * BigO:       ?
 * Difficulty: Medium
 * Techniques: DFS
 * Learnings:
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class Leet0079 {

    public static void main(String[] args) {
        char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println("ABCCED="+exists(board, "ABCCED"));
        System.out.println("SEE="+exists(board, "SEE"));
        System.out.println("ABCB="+exists(board, "ABCB"));
    }


    public static boolean exists(char[][] board, String target) {

        for (int row=0; row < board.length; row++) {
            for (int col=0; col < board[0].length; col++) {
                if (exists(board, target, 0, row, col)) {
                    return true;
                }
            }
        }


        return false;
    }


    public static boolean exists(char[][] board, String target, int length, int row, int col) {

        if (length == target.length()) return true;

        if (row < 0 || row >= board.length
         || col < 0 || col >= board[0].length
         || board[row][col] == '.'
         || board[row][col] != target.charAt(length)) {
            return false;
        }

        char atChar = board[row][col];

        board[row][col] = '.';
        boolean found = exists(board, target, length+1, row-1, col) ||
                        exists(board, target, length+1, row+1, col) ||
                        exists(board, target, length+1, row, col+1) ||
                        exists(board, target, length+1, row, col-1);

        board[row][col] = atChar;
        return found;
    }
}

