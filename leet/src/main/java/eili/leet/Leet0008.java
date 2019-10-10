package eili.leet;

/**
 * Problem #:  0008
 * Name:       Atoi
 * Tags:       Math, String, Edge Cases
 * Difficulty: Medium
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the
 * first non-whitespace character is found. Then, starting from this character, takes an
 * optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace
 * characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 * Only the space character ' ' is considered as whitespace character.
 *
 * Assume we are dealing with an environment which could only store integers within the
 * 32-bit signed integer range: [−2^31,  2^31 − 1].  If the numerical value is out of
 * the range of representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.
 *
 *
 * Example 1:
 * Input: "42"
 * Output: 42
 *
 *
 * Example 2:
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign.
 *              Then take as many numerical digits as possible, which gets 42.
 *
 *
 * Example 3:
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
 *
 *
 * Example 4:
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical
 *              digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 *
 * Example 5:
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 *              Thefore INT_MIN (−2^31) is returned.
 *
 *
 * Learnings:
 * 1. Don't get fancy with the overflow/underflow calculations.  They are tricky.
 *    Just check for the hard-coded boundary values!
 *
 * 2. Test more edge cases.
 */
public class Leet0008 {

    public static void main(String[] args) {
        System.out.println("atoi for [  +-123 wordz ]="+solution("  +-123 wordz "));
        System.out.println("atoi for [-42]="+solution("-42"));
        System.out.println("atoi for [ abc 39183]="+solution(" abc 39183"));
        System.out.println("atoi for [3.14159265]="+solution("3.14159265"));
        System.out.println("atoi for [2147483646]="+solution("2147483646"));
        System.out.println("atoi for [2147483647]="+solution("2147483647"));
        System.out.println("atoi for [2147483648]="+solution("2147483648"));
        System.out.println("atoi for [2147483800]="+solution("2147483800"));
        System.out.println("atoi for [-2147483647]="+solution("-2147483647"));
        System.out.println("atoi for [-2147483648]="+solution("-2147483648"));
        System.out.println("atoi for [-2147483649]="+solution("-2147483649"));
    }

    public static int solution(String snum) {

        int answer = 0;
        int sign   = 1;
        char[] chars  = snum.trim().toLowerCase().toCharArray();

        for (int i=0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int valToAdd = sign * (chars[i]-'0');
                if (willOverflow(answer, valToAdd))  return Integer.MAX_VALUE;
                if (willUnderflow(answer, valToAdd)) return Integer.MIN_VALUE;
                answer = answer * 10 + valToAdd;
            } else if (chars[i] == '-' && i==0) {
                sign = -1;
            } else if (chars[i] == '+' && i==0) {
                ;
            } else {
                return answer;
            }
        }

        return answer;
    }


    public static boolean willOverflow(int value, int toAdd) {
        if (value >= 214748365) return true;
        if (value >= 214748364 && toAdd > 7) return true;
        return false;
    }

    public static boolean willUnderflow(int value, int toAdd) {
        if (value <= -214748365) return true;
        if (value <= -214748364 && toAdd < -8) return true;
        return false;
    }
}
