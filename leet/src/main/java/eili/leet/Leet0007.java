package eili.leet;

/**
 * Problem #:  0007
 * Name:       Reverse Integer
 * Tags:       Math
 * Difficulty: Easy
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21

 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−231,  231 − 1].  For the purpose of this problem, assume that your
 * function returns 0 when the reversed integer overflows.
 *
 * Learnings:
 * 1. Don't get fancy with the overflow/underflow calculations.  They are tricky.
 *    Just check for the hard-coded boundary values!
 *
 */
public class Leet0007 {

    public int solution(int x) {
        int answer = 0;
        while (x != 0) {
            if (answer >=  214748365) return 0;
            if (answer >=  214748364 && (x % 10 > 7)) return 0;
            if (answer <= -214748365) return 0;
            if (answer <= -214748364 && (x % 10 < -8)) return 0;
            answer = answer * 10 + (x % 10);
            x = x / 10;
        }

        return answer;
    }

}
