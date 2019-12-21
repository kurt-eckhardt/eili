package eili.leet.leet000;

/**
 * Problem #:  0031
 * Name:       Next Permutation
 * Tags:       Math, Permutation, Array
 * BigO:       O(N)
 * Difficulty: Medium
 * Techniques: Break-It-Down
 * Learnings:
 * 1) This is another one of those, if you know the pattern, it's not that difficult, but
 *    finding the pattern is pretty hard.  Not sure how to make these easier in the future.
 *
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater
 * permutation of numbers.  If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).  The replacement must be in-place and use only
 * constant extra memory.  Here are some examples.  Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Leet0031 {
    public static void main(String[] args) {
        int[] array = new int[] {2,3,1,3,3};
        for (int i=0; i <= 6; i++) {
            nextPermutation(array);
            for (int j : array) System.out.print(j);
            System.out.println();
        }
    }

    // Intuition:
    // Build the next pattern by figuring out the next position to explore
    // would be if we were generating all patterns. The next place to explore
    // will use the last position where we made a choice about which number to
    // place.  Note the number to the left of the section that is strictly
    // decreasing is the last position where we made a choice about which number
    // to place.  Also note we can't explore any more positions after a stricly
    // decreasing section, so we must backtrack to the previous choice and pick
    // the next highest value.
    //
    // Algorithm:
    // 1. Scan right to find section where numbers are strictly decreasing.
    //    If no numbers are strictly decreasing, then use the last number.
    //    If all numbers are strictly decreasing, then reverse all digits and stop.
    // 2. Note the number to the left of the strictly decreasing section (SDD).
    //    Lets call this "leftOfSD".
    // 3. Swap leftOfSD with next higest value found in SDD.
    // 4. Reverse all values in SDD so they form the smallest permutation.
    //
    // 0123 => 0132
    // 0132 => 0231 -> 0213
    // 0213 => 0231
    // 0231 => 0231 -> 0321 -> 0312
    // 0312 => 0321
    // 0321 => 1320 -> 1023
    // 3210 => 0123
    public static int[] nextPermutation(int[] input) {

        // Find start index of the strictly decreasing section
        int startOfSDIdx = input.length-1;
        while (startOfSDIdx > 0 && input[startOfSDIdx-1] >= input[startOfSDIdx]) {
            startOfSDIdx--;
        }

        if (startOfSDIdx == 0) {
            return reverse(input, 0);
        } else {
            int minIndex = startOfSDIdx;
            int minSoFar = input[startOfSDIdx];
            int valBeforeSD = input[startOfSDIdx-1];
            for (int i = startOfSDIdx; i < input.length; i++) {
                if (input[i] > valBeforeSD && input[i] <= minSoFar) {
                    minSoFar = input[i];
                    minIndex = i;
                }
            }

            swap(input, startOfSDIdx-1, minIndex);
            return reverse(input, startOfSDIdx);
        }
    }

    public static int[] reverse(int[] input, int sIdx) {
        int eIdx = input.length - 1;
        while (sIdx < eIdx) {
            swap(input, sIdx++, eIdx--);
        }

        return input;
    }

    public static void swap(int[] input, int idx1, int idx2) {
        int tempval = input[idx1];
        input[idx1] = input[idx2];
        input[idx2] = tempval;
    }
}
