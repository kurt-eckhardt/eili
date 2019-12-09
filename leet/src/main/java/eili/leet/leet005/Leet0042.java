package eili.leet.leet005;

import java.util.ArrayDeque;

/**
 * Problem #:  0042
 * Name:       Trapping Rain Water
 * Tags:       Array
 * BigO:       Time O(N), Space - Memoization: O(N), Two-Pointers: O(1)
 * Difficulty: Hard
 * Techniques: Two-Pointers, Memoization
 * Learnings:  Thinking critically about how we knew how much water a column can hold.  See how
 *             the water is bounded by the maxiumum column height to the left and to the right?
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units
 * of rain water (blue section) are being trapped.
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class Leet0042 {

    public static void main(String[] args) {
        System.out.println(trap2(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap3(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }


    public static int trap3(int[] blocks) {

        int tot  = 0;
        int lIdx = 0;
        int rIdx = blocks.length-1;

        while (lIdx < rIdx && blocks[lIdx] == 0) lIdx++;
        while (rIdx > lIdx && blocks[rIdx] == 0) rIdx--;

        while (lIdx < rIdx) {
            if (blocks[lIdx] <= blocks[rIdx]) {
                int currIdx = lIdx;
                while (currIdx < rIdx && blocks[currIdx] <= blocks[lIdx]) {
                    tot += blocks[lIdx] - blocks[currIdx];
                    currIdx++;
                }
                lIdx = currIdx;
            } else {
                int currIdx = rIdx;
                while (currIdx > lIdx && blocks[currIdx] <= blocks[rIdx]) {
                    tot += blocks[rIdx] - blocks[currIdx];
                    currIdx--;
                }
                rIdx = currIdx;
            }
        }

        return tot;
    }


    public static int trap2(int[] bArray) {

        int totalVol   = 0;
        int maxOnLeft  = 0;
        int maxOnRight = 0;
        int[] lVolAtX = new int[bArray.length];

        for (int i=0; i < bArray.length; i++) {
            lVolAtX[i] = maxOnLeft - bArray[i] > 0 ? maxOnLeft - bArray[i] : 0;
            maxOnLeft  = Math.max(maxOnLeft, bArray[i]);
        }

        for (int i=bArray.length-1; i >=0;i--) {
            int rVol = maxOnRight - bArray[i] > 0 ? maxOnRight - bArray[i] : 0;
            totalVol += Math.min(lVolAtX[i], rVol);
            maxOnRight = Math.max(maxOnRight, bArray[i]);
        }

        return totalVol;
    }




    public static int trapWater(int[] bArray) {

        int totVolume  = 0;
        int lastHeight = 0;
        ArrayDeque<Block> stack = new ArrayDeque<>();
        for (int x=0; x < bArray.length; x++) {

            if (bArray[x] <= 0) continue;
            Block aBlock = new Block(x, bArray[x]);

            while (!stack.isEmpty() && stack.peek().h <= aBlock.h) {
                int width = aBlock.x - stack.peek().x - 1;
                int minH  = Math.min(aBlock.h, stack.peek().h);
                totVolume += width * (minH - lastHeight);
                lastHeight = stack.pop().h;
            }

            stack.push(aBlock);
        }

        return totVolume;
    }



    public static class Block {
        int x;
        int h;
        public Block(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
}
