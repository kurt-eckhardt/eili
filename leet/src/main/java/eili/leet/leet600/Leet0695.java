package eili.leet.leet600;

/**
 * Problem #:  0695
 * Name:       Max Area of Island
 * Tags:       Graph, 2D Matrix, Traversal
 * BigO:       Time O(NxM), Space O(NxM)
 * Difficulty: Medium (Easy)
 * Techniques: BFS Traversal
 * Similar To: 0200
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid
 * are surrounded by water.  Find the maximum area of an island in the given 2D array.
 * (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 */
public class Leet0695 {

    public int numIslands(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row=0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    int area = exploreIsland(grid, row, col);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }


    private int exploreIsland(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length
                || col < 0 || col >= grid[0].length
                || grid[row][col] != 1) {
            return 0;
        } else {
            grid[row][col] = 0;
            return 1
                 + exploreIsland(grid, row-1, col)  // L
                 + exploreIsland(grid, row+1, col)  // R
                 + exploreIsland(grid, row, col-1)  // U
                 + exploreIsland(grid, row, col+1); // D
        }
    }
}
