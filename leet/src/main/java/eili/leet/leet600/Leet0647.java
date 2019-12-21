package eili.leet.leet600;

/**
 * Problem #:  0647
 * Name:       Palindromic Substrings
 * Tags:       String, Matrix, Array, Palindrome
 * BigO:       O(N^2) Time, O(N^2) Space
 * Difficulty: Medium
 * Techniques: Dynamic Programming, Bottom-Up, Memoization?
 * Learnings:
 * 1) Checkout the solution to Leet0005 which is almost identical.  That code is cleaner.
 *
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different
 * substrings even they consist of same characters.
 *
 * Note:  The input string length won't exceed 1000.
 *
 * Example 1:  Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 * Example 2:  Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 */
public class Leet0647 {

    public static void main(String[] args) {
        System.out.println("num pali substrings in ''= " + countSubstrings(""));
        System.out.println("num pali substrings in 'abc'= " + countSubstrings("abc"));
        System.out.println("num pali substrings in 'aba'= " + countSubstrings("aba"));
        System.out.println("num pali substrings in 'aaa'= " + countSubstrings("aaa"));
        System.out.println("num pali substrings in 1000x'a'= " + countSubstrings("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }


    public static int countSubstrings(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int count   = 0;
        int sLength = s.length();
        boolean[][] isPali = new boolean[sLength+1][sLength];
        for (int groupSize=1; groupSize <= sLength; groupSize++) {
            for (int groupStart=0; groupStart <= sLength - groupSize; groupStart++) {
                int groupEnd = groupStart + groupSize - 1;
                if (s.charAt(groupStart) == s.charAt(groupEnd)) {
                    if (groupEnd - groupStart < 2) {
                        isPali[groupStart][groupEnd] = true;
                        count++;
                    } else if (isPali[groupStart+1][groupEnd-1]) {
                        isPali[groupStart][groupEnd] = true;
                        count++;
                    } else {
                        isPali[groupStart][groupEnd] = false;
                    }
                } else {
                    isPali[groupStart][groupEnd] = false;
                }
            }
        }

        return count;
    }

}
