package eili.leet.leet400;


/**
 * Problem #:  0463
 * Name:       Island Perimeter
 * Tags:       2D Matrix, Array
 * BigO:       Time O(NxM), Space O(1)
 * Difficulty: Easy
 * Techniques: Array Traversal
 * Similar To: 0695
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is
 * a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter
 * of the island.
 *
 * Example:
 *
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Output: 16
 */
public class Leet0463 {

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row=0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    if (row == 0      || grid[row-1][col] == 0) perimeter++;
                    if (row == rows-1 || grid[row+1][col] == 0) perimeter++;
                    if (col == 0      || grid[row][col-1] == 0) perimeter++;
                    if (col == cols-1 || grid[row][col+1] == 0) perimeter++;
                }
            }
        }

        return perimeter;
    }
}
