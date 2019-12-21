package eili.leet.leet500;

/**
 * Problem #:  0521
 * Name:       Longest Uncommon Subsequence I
 * Tags:       Math, Power Set, String, Subsequence
 * BigO:       O(C) where 'C' is the number of characters in the shorter String
 * Difficulty: Easy?
 * Techniques:
 * Learnings:
 * 1) This is an april fools joke.  The problem is poorly worded and a waste of time.  The real
 *    "solution" is to take the longer of the two strings so long as they don't equal each other.
 *
 *
 * Given a group of two strings, you need to find the longest uncommon subsequence of this group
 * of two strings.  The longest uncommon subsequence is defined as the longest subsequence of one
 * of these strings and this subsequence should not be any subsequence of the other strings.
 *
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters
 * without changing the order of the remaining elements. Trivially, any string is a subsequence of
 * itself and an empty string is a subsequence of any string.
 *
 * The input will be two strings, and the output needs to be the length of the longest uncommon
 * subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 *
 * Example 1:
 * Input: "aba", "cdc"
 * Output: 3
 *
 * Explanation: The longest uncommon subsequence is "aba" (or "cdc"), because "aba"
 * is a subsequence of "aba", but not a subsequence of any other strings in the group of two strings.
 *
 * Note:
 * Both strings' lengths will not exceed 100.
 * Only letters from a ~ z will appear in input strings.
 */
public class Leet0521 {
    public static void main(String[] args) {
        System.out.println(longestUCS("abafo",""));
    }

    public static int longestUCS(String a, String b) {

        if (a == null || b == null) {
            return -1;
        }

        int[] seen = new int[26];
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        for (char c : a.toCharArray()) {
            seen[c-'a']=1;
        }

        int currMax  = 0;
        int maxSoFar = 0;
        for (char c : b.toCharArray()) {
            if (seen[c-'a'] == 0) {
                maxSoFar = Math.max(maxSoFar, ++currMax);
            } else {
                currMax = 0;
            }
        }

        return (maxSoFar == 0 ? -1 : maxSoFar);
    }
}
