package eili.leet;

/**
 * Problem #:  0009
 * Name:       Palindrome Number
 * Tags:
 * Difficulty: Easy
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome when
 * it reads the same backward as forward.
 *
 * Example 1:
 * Input: 121
 * Output: true
 *
 *
 * Example 2:
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-.
 * Therefore it is not a palindrome.
 *
 *
 * Example 3:
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 *
 * Follow up:
 * Coud you solve it without converting the integer to a string?
 *
 *
 * Learnings:
 *
 */
public class Leet0009 {

    public static void main(String[] args) {
        System.out.println("solution for 0="+solution(0));
        System.out.println("solution for 1="+solution(1));
        System.out.println("solution for 10="+solution(10));
        System.out.println("solution for 1221="+solution(1221));
        System.out.println("solution for 12321="+solution(12321));
        System.out.println("solution for 1223421="+solution(1223421));
        System.out.println("solution for MAX_VALUE="+solution(Integer.MAX_VALUE));
    }

    // 121
    // 238832  => 238 - 238 = 0
    // 2347432 => 234 - 234 = 0
    // 1. get num digits
    // 2. build mirror number using n/2 digits
    // 3. num is a plindrome if split - mirror == 0
    public static boolean solution(int num) {

        if (num < 0) return false;
        if (num < 10) return true;
        int numDigits = (int)Math.log10(num)+1;

        int loDigits = 0;
        for (int i=0; i < numDigits/2; i++) {
            loDigits = loDigits * 10 + (num % 10);
            num = num / 10;
        }

        // remove "middle" digit if necessary
        if (numDigits % 2 == 1) num = num / 10;

        return loDigits - num == 0;
    }
}
