package eili.leet.leet300;

/**
 * Problem #:  0329
 * Name:       Longest Increasing Path
 * Tags:
 * BigO:       Time O(NxM), Space O(NxM)
 * Difficulty: Hard
 * Techniques: Memoization
 * Learnings:
 *
 * Problem Description:
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 */
public class Leet0329 {

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxLong  = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int row=0; row < matrix.length; row++) {
            for (int col=0; col < matrix[0].length; col++) {
                maxLong = Math.max(maxLong, longest(row, col, matrix, memo));
            }
        }

        // for (int row=0; row < matrix.length; row++) {
        //     for (int col=0; col < matrix[0].length; col++) {
        //         System.out.print(memo[row][col] + " ");
        //     }
        //     System.out.println();
        // }

        return maxLong;
    }

    public int longest(int row, int col, int[][] grid, int[][] memo) {

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 0;
        } else if (memo[row][col] > 0) {
            return memo[row][col];
        }

        int max   = 1;
        int atVal = grid[row][col];
        if (row > 0 && atVal < grid[row-1][col]) {
            max = Math.max(max, longest(row-1, col, grid, memo)+1);
        }
        if (row < grid.length-1 && atVal < grid[row+1][col]) {
            max = Math.max(max, longest(row+1, col, grid, memo)+1);
        }
        if (col > 0 && atVal < grid[row][col-1]) {
            max = Math.max(max, longest(row, col-1, grid, memo)+1);
        }
        if (col < grid[0].length-1 && atVal < grid[row][col+1]) {
            max = Math.max(max, longest(row, col+1, grid, memo)+1);
        }

        memo[row][col] = max;
        return max;
    }

}
