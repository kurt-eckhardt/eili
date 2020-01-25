package eili.leet.leet200;

/**
 * Problem #:  0200
 * Name:       Number of Islands
 * Tags:       Graph, 2D Matrix, Traversal
 * BigO:       Time O(NxM), Space O(NxM)
 * Difficulty: Medium (Easy)
 * Techniques: BFS Traversal
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 */
public class Leet0200 {

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row=0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                if (grid[row][col] == '1') {
                    numIslands++;
                    exploreIsland(grid, row, col);
                }
            }
        }

        return numIslands;
    }


    private void exploreIsland(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length
                || col < 0 || col >= grid[0].length
                || grid[row][col] != '1') {
            return;
        } else {
            grid[row][col]='x';
            exploreIsland(grid, row-1, col); // L
            exploreIsland(grid, row+1, col); // R
            exploreIsland(grid, row, col-1); // U
            exploreIsland(grid, row, col+1); // D
        }
    }
}
