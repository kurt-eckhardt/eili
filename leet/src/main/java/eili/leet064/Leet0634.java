package eili.leet064;

/**
 * Problem #:  0634
 * Name:       Num Derangements
 * Tags:       Math, Permutations
 * Difficulty: Medium
 * Techniques: Dynamic Programming
 * Learnings:
 *
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set
 * such that no element appears in its original position.  Find the number of derangements
 * given an array of 1 to n in ascending order.
 *
 * Another way of asking this question (per Wikipedia) is:
 * You have 6 balls in 6 different colors, and for every ball you have a box of the same
 * color. How many derangements do you have, if no ball is in a box of the same color?
 *
 * Example 1:
 * Input: 3
 * Output: 2
 *
 * Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
 */
public class Leet0634 {

    public static void main(String[] args) {
        for (int i=0; i < 10; i++) {
            System.out.println("Number of derangements for n="+i+": " + numDerangements(i));
        }
    }

    /**
     * Approach:
     * Consider n=4 [1,2,3,4]
     *
     * The number 1 can be placed in one of other 3 (n-1) slots (2,3,4).  Let's call this index 'i'.
     * Then the remaining items can be deranged in one of two ways:
     *
     * 1) If the element at i is swapped with '1', then there are n-2 derangements for the remaining elements
     * 2) If it is not swapped with '1', then there n-1 derangmenets since there are n-1 elements, n-1 slots and
     *    every element has n-2 choices
     *
     * So, the formula is (n-1) * (numDerangements(n-1) * numDerangements(n-2))
     *
     * @param n
     * @return
     */
    public static long numDerangements(int n) {

        if (n < 2) {
            return ~n & 0x1;
        }

        long[] derange = new long[n+1];
        derange[0] = 0;
        derange[1] = 0;
        derange[2] = 1;
        for (int i=3; i <= n; i++) {
            derange[i] = (i-1) * (derange[i-1] + derange[i-2]);
        }

        return derange[n];
    }
}
