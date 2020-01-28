package eili.leet.leet000;

import java.util.ArrayDeque;

/**
 * Problem #:  0084
 * Name:       Largest Rectangle in Histogram
 * Tags:       Stack
 * BigO:       Time O(N), Space O(N)
 * Difficulty: Hard
 * Techniques: Monotonic Stack
 * Similar To:
 * Learnings:
 * 1) For all items on the stack that are taller than the item at the current
 *    index, we can caculate the area the bar covered using:
 *    a. width  = current index (i) - index where bar started
 *    b. height = saved bar height
 *
 * 2) We need "save" the height of the current bar in the left most bar that
 *    was taller than the current item (because it may extend further to the right)
 *
 *
 * Problem Description:
 * Given n non-negative integers representing the histogram's bar height where the
 * width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 *              ___
 *          ___| 6 |
 *         | 5 |   |
 *         |   |   |    ___
 *  ___    |   |   |___| 3 |
 * | 2 |___|   |   | 2 |   |
 * |   | 1 |   |   |   |   |
 * -------------------------
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3]
 *
 *              ___
 *          ___| 6 |
 *         |#5#|###|
 *         |###|###|    ___
 *  ___    |###|###|___| 3 |
 * | 2 |___|###|###| 2 |   |
 * |   | 1 |###|###|   |   |
 * -------------------------
 *
 * Max Area = 10 covers bars of height 5 and 6.
 */
public class Leet0084 {

    public int largestRectangleArea(int[] heights) {

        int max = 0;
        if (heights == null || heights.length == 0) return 0;

        ArrayDeque<int[]> bars = new ArrayDeque<>();
        for (int i=0; i < heights.length; i++) {
            int farLeftIdx = i;
            while (!bars.isEmpty() && bars.peek()[0] > heights[i]) {
                farLeftIdx = bars.peek()[1];
                int width  = i - bars.peek()[1];
                int height = bars.pop()[0];
                max = Math.max(max, width*height);
            }
            bars.push(new int[] { heights[i], farLeftIdx });
        }

        // rest are strictly increasing all the way to the end
        while (!bars.isEmpty()) {
            int width  = heights.length - bars.peek()[1];
            int height = bars.pop()[0];
            max = Math.max(max, width*height);
        }

        return max;
    }

}
