package eili.leet.leet101;

/**
 * Problem #:  1001
 * Name:       Minimum Domino Rotations
 * Tags:       Google Question
 * BigO:       O(N)
 * Difficulty: Medium (Easy?)
 * Techniques: Break-It-Down
 * Learnings:
 *
 * A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the
 * values in B are the same.
 *
 * If it cannot be done, return -1
 *
 * For Example:
 * Given: 2,1,2,4,2,2
 *        5,2,6,2,3,2
 *
 * Output: 2 (flip domino #3 and #4 so that all 2's line up.
 */
public class Leet1001 {

    public static void main(String[] args) {
        System.out.println(minDominoRotations(new int[] {2,1,2,4,2,2}, new int[] {5,2,6,2,3,2}));
    }

    public static int minDominoRotations(int[] A, int[] B) {

        if (A == null || B == null || A.length == 0 || B.length != A.length) {
            return -1;
        }

        int flipVal = 0;
        int[] aValCount = new int[7];
        int[] bValCount = new int[7];

        // find double dominos (same number on both size)
        // if there is > 1 then there is no solution.
        for (int i=0; i < A.length; i++) {

            aValCount[A[i]]++;
            bValCount[B[i]]++;

            if (A[i] == B[i]) {
                if (flipVal == 0) {
                    flipVal = A[i];
                } else if (flipVal != A[i]) {
                    return -1;
                }
            }
        }


        // if there is a double domino, determine if row a or b
        // has more values in their row.  flip the one with less.
        if (flipVal != 0) {
            // ensure all dominos have at least 1 size with flipVal
            for (int i=0; i < A.length; i++) {
                if (A[i] != flipVal && B[i] != flipVal) {
                    return -1;
                }
            }

            return A.length - Math.max(aValCount[flipVal], bValCount[flipVal]);
        }

        // if there is no double, then find numbers that have
        // 'n' of the same value.  There must be 0, 1, or 2
        // if zero, then there is no solution.
        // if 1 or 2, then flip the row with the fewest dominos
        for (int i=1; i <= 6; i++) {
            if (aValCount[i]+bValCount[i] == A.length) {
                return A.length - Math.max(aValCount[i], bValCount[i]);
            }
        }

        return -1;
    }
}
