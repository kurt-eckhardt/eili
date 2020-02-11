package eili.leet.leet000;

/**
 * Problem #:  0029
 * Name:       Divide Two Integers
 * Tags:       Bit manipulation
 * BigO:       Time O(dividen / divsor), Space O(1)
 * Difficulty: Medium (Easy?)
 * Techniques: Bit manipulation
 * Learnings:  Quick division.
 *             Use powers of 2 to quickly determine the maximum number of times a divisor
 *             can fit into the numerator.  Rinse and repeat for remaining values until
 *             the numerator is smaller than the divisor.
 *
 * Problem Description:
 * Given two integers dividend and divisor, divide two integers without using
 * multiplication, division and mod operator.  Return the quotient after dividing
 * dividend by divisor.  The integer division should truncate toward zero.
 *
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.  The divisor will never
 * be 0.  Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−2^31,  2^31 − 1].  For the purpose of this
 * problem, assume that your function returns 2^31 − 1 when the division result overflows.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 */
public class Leet0029 {

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -2));
        System.out.println(divide(-2147483648,  2));
        System.out.println(divide(-2147483648, -1));
        System.out.println(divide(-2147483648,  1));
        System.out.println(divide(2147483647,   3));
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        } else if (divisor == -1) {
            // per requirements, we should return 2^31 - 1 for overflow
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return ~dividend + 1;
            }
        }


        if (divisor == 2) {
            return dividend >> 1;
        } else if (divisor == -2) {
            return ~(dividend >> 1) + 1;
        }


        int result = 0;
        int negdividen = (dividend < 0) ? dividend : ~dividend + 1;
        int negdivisor = (divisor < 0)  ? divisor  : ~divisor  + 1;
        while (negdividen <= negdivisor) {
            result++;
            negdividen -= negdivisor;
        }

        boolean isNegative = dividend < 0 ^ divisor < 0;
        return isNegative ? ~result + 1 : result;
    }


    /**
     * Learnings: How to divide using base 2
     *
     * Example: 10523 / 71 = ?
     *
     * Iteration #1: 10523 / 71
     * 1	71
     * 2	142
     * 4	284
     * 8	568
     * 16	1136
     * 32	2272
     * 64	4544
     * 128	9088  (ok)
     * 256	18176 (too big!)
     * 10523 - 9088 = 1435
     *
     *
     * Iteration #2: 1435 / 71
     * 16	1136 (max times 71 fits)
     * 32	2272 (too big!)
     * 1435 - 1136 = 299
     *
     * Iteration #3: 299 / 71
     * 1	71
     * 2	142
     * 4	284
     * 8	568! (too big!)
     * 299 - 284 = 15
     *
     * Iteration #4: 15 / 71
     * 15 / 71 = 0
     *
     * Answer = 128 + 16 + 4 = 148
     */
}
