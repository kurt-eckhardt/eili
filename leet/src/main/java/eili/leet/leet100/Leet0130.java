package eili.leet.leet100;

/**
 * Problem #:  0130
 * Name:       Surrounded Regions
 * Tags:       2D Matrix, Array
 * BigO:       Time O(NxM), Space O(1)
 * Difficulty: Medium (Easy)
 * Techniques: Array Traversal
 * Similar To: 0463
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class Leet0130 {

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Save all regions starting on an edge
        for (int row=0; row < rows; row++) {
            fill(row, 0,      board, 'O','s');
            fill(row, cols-1, board, 'O','s');
        }
        for (int col=0; col < cols; col++) {
            fill(0,      col, board, 'O','s');
            fill(rows-1, col, board, 'O','s');
        }

        // Now Flip all remaining 'O's to 'X' because they are surrounded
        for (int row=1; row < rows-1; row++) {
            for (int col=1; col < cols-1; col++) {
                fill(row, col, board, 'O','X');
            }
        }

        // Finally Flip all saved 's' back to 'O'
        for (int row=0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                fill(row, col, board, 's','O');
            }
        }
    }


    public void fill(int row, int col, char[][] board, char from, char to) {
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length
                || board[row][col] != from) {
            return;
        } else {
            board[row][col] = to;
            fill(row-1, col, board, from, to);
            fill(row+1, col, board, from, to);
            fill(row, col-1, board, from, to);
            fill(row, col+1, board, from, to);
        }
    }
}
