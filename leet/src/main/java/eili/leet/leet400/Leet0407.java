package eili.leet.leet400;


import java.util.PriorityQueue;

/**
 * Problem #:  0407
 * Name:       Trapping Rain Water II
 * Tags:       Heap, Traversal
 * BigO:       Time O(N*M), Space O(N*M)
 * Difficulty: Hard
 * Techniques: Traversal
 * Learnings:  Use a PriorityQueue / MinHeap to keep track of which nodes to visit next.
 *
 * Given an m x n matrix of positive integers representing the height of each unit cell
 * in a 2D elevation map, compute the volume of water it is able to trap after raining.
 *
 * Note:
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is
 * less than 20,000.
 */
public class Leet0407 {


    public static int trap(int[][] boxes) {

        int numRows = boxes.length;
        int numCols = boxes[0].length;

        boolean[][] visited = new boolean[boxes.length][boxes[0].length];
        PriorityQueue<Box> minHeap = new PriorityQueue<>((b1, b2) -> {
            if (b1.height >= b2.height) {
                return 1;
            } else {
                return -1;
            }
        });

        for (int row=0; row < numRows; row++) {
            visited[row][0]         = true;
            visited[row][numCols-1] = true;
            minHeap.add(new Box(row, 0, boxes[row][0]));
            minHeap.add(new Box(row, numCols-1, boxes[row][numCols-1]));
        }

        for (int col=0; col < numCols; col++) {
            visited[0][col]         = true;
            visited[numRows-1][col] = true;
            minHeap.add(new Box(0, col, boxes[0][col]));
            minHeap.add(new Box(numRows-1, col, boxes[numRows-1][col]));
        }

        int totVolume = 0;
        int maxHeight = Integer.MIN_VALUE;

        while (!minHeap.isEmpty()) {
            Box visiting = minHeap.remove();
            if (visiting.height >= maxHeight) {
                maxHeight = visiting.height;
            } else {
                totVolume += (maxHeight - visiting.height);
            }

            if (visiting.row > 0 && !visited[visiting.row-1][visiting.col]) {
                minHeap.add(new Box(visiting.row-1, visiting.col, boxes[visiting.row-1][visiting.col]));
                visited[visiting.row-1][visiting.col] = true;
            }

            if (visiting.row < numRows-1 && !visited[visiting.row+1][visiting.col]) {
                minHeap.add(new Box(visiting.row+1, visiting.col, boxes[visiting.row+1][visiting.col]));
                visited[visiting.row+1][visiting.col] = true;
            }

            if (visiting.col > 0 && !visited[visiting.row][visiting.col-1]) {
                minHeap.add(new Box(visiting.row, visiting.col-1, boxes[visiting.row][visiting.col-1]));
                visited[visiting.row][visiting.col-1] = true;
            }

            if (visiting.col < numCols-1 && !visited[visiting.row][visiting.col+1]) {
                minHeap.add(new Box(visiting.row, visiting.col+1, boxes[visiting.row][visiting.col+1]));
                visited[visiting.row][visiting.col+1] = true;
            }
        }

        return totVolume;
    }



    public static class Box {
        int row = 0;
        int col = 0;
        int height = 0;
        public Box(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        public String toString() {
            return "Box["+row+"]["+col+"] h="+height;
        }
    }


    // This doesn't work because water will run out of adjacent rows/colums
    // if their heights are lower than the maxiumum bounding a cell.
    // eg.:
    //         int[][] heightMap3 = new int[][] {
    //                {12,13, 1,12},   // 0 0 0 0
    //                {13, 4,13,12},   // 0 8 0 0
    //                {13, 8,10,12},   // 0 4 2 0
    //                {12,13,12,12},   // 0 0 0 0
    //                {13,13,13,13}    // 0 0 0 0
    //        };
    // In the above, cell[1][1] can only hold 8 units, not 9 since
    // cell[3][2] is height 12
    //
    public static int trapBad(int[][] boxes) {

        if (boxes.length == 0 || boxes[0].length == 0) return 0;

        int[][] maxLeft  = new int[boxes.length][boxes[0].length];
        int[][] maxRight = new int[boxes.length][boxes[0].length];
        int[][] maxAbove = new int[boxes.length][boxes[0].length];
        int[][] maxBelow = new int[boxes.length][boxes[0].length];

        // Left
        for (int row=0; row < boxes.length; row++) {
            for (int col=1; col < boxes[0].length; col++) {
                maxLeft[row][col] = Math.max(maxLeft[row][col-1], boxes[row][col-1]);
            }
        }

        // Right
        for (int row=0; row < boxes.length; row++) {
            for (int col=boxes[0].length-2; col >= 0; col--) {
                maxRight[row][col] = Math.max(maxRight[row][col+1], boxes[row][col+1]);
            }
        }

        // Above
        for (int col=0; col < boxes[0].length; col++) {
            for (int row=1; row < boxes.length; row++) {
                maxAbove[row][col] = Math.max(maxAbove[row-1][col], boxes[row-1][col]);
            }
        }

        // Below
        for (int col=0; col < boxes[0].length; col++) {
            for (int row=boxes.length-2; row >=0; row--) {
                maxBelow[row][col] = Math.max(maxBelow[row+1][col], boxes[row+1][col]);
            }
        }

        int tot = 0;
        for (int row=0; row < boxes.length; row++) {
            for (int col=0; col < boxes[0].length; col++) {
                int minLR = Math.min(maxLeft[row][col],  maxRight[row][col]);
                int minAB = Math.min(maxAbove[row][col], maxBelow[row][col]);
                int water = Math.min(minLR, minAB) - boxes[row][col];
                if (water > 0) tot += water;
                System.out.print(water + " ");
            }
            System.out.println();
        }

        return tot;
    }


    public static void main(String[] args) {
        int[][] heightMap1 = new int[][] {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };

        int[][] heightMap2 = new int[][] {
                {12,13, 1,12,13,13},      // 0 0 0 0
                {13, 4,13,12, 8,13},      // 0 9 0 0
                {13, 6, 8,12, 8,13},      // 0 4 2 0
                {12,13, 8,12,12,13},      // 0 0 0 0
                {13, 3, 4, 5, 8,13},      // 0 0 0 0
                {13,13, 9,13,13,13}       // 0 0 0 0
        };

        int[][] heightMap3 = new int[][] {
                {12,13, 1,12},   // 0 0 0 0
                {13, 4,13,12},   // 0 8 0 0
                {13, 8,10,12},   // 0 4 2 0
                {12,13,12,12},   // 0 0 0 0
                {13,13,13,13}    // 0 0 0 0
        };

        int[][] heightMap4 = new int[][] {
                {9,9,9,9,9},   // 0 0 0 0 0
                {9,2,1,2,9},   // 0 7 8 7 0
                {9,2,8,2,9},   // 0 7 1 7 0
                {9,2,3,2,9},   // 0 7 6 7 0
                {9,9,9,9,9}    // 0 0 0 0 0  tot=57
        };

        System.out.println("Trap2="+trap(heightMap4));
    }

}
