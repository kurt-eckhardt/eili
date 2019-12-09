package eili.leet.leet041;

public class Leet0407 {

    public static void main(String[] args) {
        int[][] heightMap1 = new int[][] {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };

        int[][] heightMap2 = new int[][] {
                {12,13,1,12},       // 0 0 0 0
                {13,4,13,12},       // 0 9 0 0
                {13,8,10,12},       // 0 2 2 0
                {12,13,12,12},      // 0 0 0 0
                {13,13,13,13}       // 0 0 0 0
        };

        System.out.println("Trapped="+trap(heightMap2));
    }

    public static int trap(int[][] boxes) {

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
                int trap  = Math.min(minLR, minAB) - boxes[row][col];
                if (trap > 0) tot += trap;
            }
        }

        return tot;
    }

}
