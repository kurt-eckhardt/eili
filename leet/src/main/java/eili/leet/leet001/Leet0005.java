package eili.leet.leet001;

/**
 * Problem #:  0005
 * Name:       Longest Palindromic Substring
 * Tags:       String, Matrix, Array, Palindrome
 * BigO:       O(N^2) Time,  O(N^2) Space
 * Difficulty: Medium
 * Techniques: Dynamic Programming, Memoization, Bottom-Up
 * Learnings:
 *
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 *
 * Learnings:
 * 1) Use a 2D Array to memoize [start][end] substring positions.
 * 2) If the solution seems overly complicated, then it probably isn't the right path.
 *
 */
public class Leet0005 {

    public static void main(String[] args) {
        System.out.println("Solution to []="+solution(""));
        System.out.println("Solution to [a]="+solution("a"));
        System.out.println("Solution to [cbbd]="+solution("cbbd"));
        System.out.println("Solution to [babad]="+solution("babad"));
        System.out.println("Solution to [xabafefax]="+solution("xabafefax"));
        System.out.println("Solution to [xabafefabax]="+solution("xabafefabax"));
        System.out.println("Solution to [xabaf efabax]="+solution("xabaf efabax"));
        System.out.println("Solution to [nopali]="+solution("nopali"));
    }


    public static String solution(String str) {
        int maxSta = 0;
        int maxEnd = 0;
        int strlen = str.length();
        if (strlen < 2) return str;

        boolean[][] isPali = new boolean[strlen][strlen];
        char[] chars = str.toCharArray();

        for (int substrLen=1; substrLen <= strlen; substrLen++) {
            for (int sIdx=0; sIdx < strlen-substrLen+1;sIdx++) {
                int eIdx = sIdx+substrLen-1;
//                System.out.println("sIdx="+sIdx + " eIdx="+eIdx + " substrLen="+substrLen);
                if (substrLen < 2) {
                    isPali[sIdx][eIdx] = true;
                    maxSta = sIdx;
                    maxEnd = eIdx;
                } else if (substrLen == 2 && chars[sIdx] == chars[eIdx]) {
                    isPali[sIdx][eIdx] = true;
                    maxSta = sIdx;
                    maxEnd = eIdx;
                } else if (chars[sIdx] == chars[eIdx] && isPali[sIdx+1][eIdx-1]) {
                    isPali[sIdx][eIdx] = true;
                    maxSta = sIdx;
                    maxEnd = eIdx;
                } else {
                    isPali[sIdx][eIdx] = false;
                }
            }
        }

        return str.substring(maxSta, maxEnd+1);
    }
}
