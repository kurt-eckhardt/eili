package eili.leet.leet1100;

/**
 * Problem #:  1323
 * Name:       Maximum 69 Number
 * Tags:       Math
 * BigO:       Time O(1), Space O(D) digits
 * Difficulty: Easy
 * Techniques:
 * Learnings:
 *
 * Given a positive integer num consisting only of digits 6 and 9.
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 * Example 1:
 * Input: num = 9669
 * Output: 9969
 */
public class Leet1323 {

    public int maximum69Number(int num) {
        int[] digits = new int[5];
        int numDigits = 0;
        for (int i = 0; i < 5; i++) {
            if (num > 0) {
                numDigits++;
                digits[i] = num % 10;
            } else {
                break;
            }
            num = num / 10;
        }

        int max = 0;
        boolean first = true;
        for (int i = numDigits; i >= 0; i--) {
            if (digits[i] == 6 && first) {
                max += 9 * Math.pow(10, i);
                first = false;
            } else {
                max += digits[i] * Math.pow(10, i);
            }
        }

        return max;
    }
}
