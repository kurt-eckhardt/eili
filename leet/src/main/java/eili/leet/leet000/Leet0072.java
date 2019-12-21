package eili.leet.leet000;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0072
 * Name:       Levenshtein Distance
 * Tags:
 * BigO:       O(N*M) Time, O(N*M) Space
 * Difficulty: Hard
 * Techniques: Dynamic Programming, Memoization, Bottom Up, Longest Common Subsequence
 * Learnings:
 * 1) Work towards base cases by comparing just the last characters.  What are rules to remove these chars?
 *    This was similar to the Longest Common Subsequence solution.
 * 2) Needed more use cases to come up with the correct algorithm
 * 3) The bottom-up solution was easier to implement once I had a working top-down (recursive) algorithm.
 *     "" h  o  r  s  e
 *  "" 0  1  2  3  4  5
 *  r  1  1  2  2  3  4
 *  o  2  2  1  2  3  4
 *  s  3  3  2  2  2  3    <- add  ^remove^   ^-replace
 *
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 */
public class Leet0072 {

    public static void main(String[] args) {
        System.out.println(lev2("abc","axc"));
        System.out.println(lev2("ros","horse"));
        System.out.println(lev2("intention","execution"));
        System.out.println(lev2("sea","eat"));
        System.out.println(lev2("park","spake"));
        System.out.println(lev2("plasma","altruism"));
        System.out.println(lev2("dinitrophenylhydrazine","benzalphenylhydrazone"));
    }

    private static Map<String, Integer> levMemos = new HashMap<>();

    /**
     * lev(ab,ax) = min(lev(ab,a), lev(a,ax), lev(a,a)) // insert, remove, replace
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int lev(String str1, String str2) {

        if (str1 == null || str2 == null) {
            return 0;
        }

        int str1Len = str1.length();
        int str2Len = str2.length();
        String key  = str1 + "_" + str2;

        if (levMemos.containsKey(key)) {
            return levMemos.get(key);
        } else if (str1.equals("")) {
            return str2.length();
        } else if (str2.equals("")) {
            return str1.length();
        } else if (str1.charAt(str1Len-1) == str2.charAt(str2Len-1)) {
            return lev(str1.substring(0, str1Len-1), str2.substring(0, str2Len-1));
        } else {
            int min = 1 + min(
                    lev(str1.substring(0, str1Len-1), str2.substring(0, str2Len-1)),  // replace
                    lev(str1, str2.substring(0, str2Len-1)),                          // insert
                    lev(str1.substring(0, str1Len-1), str2));                         // remove
            levMemos.put(key, min);
            return min;
        }
    }


    public static int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }


    // Bottom-Up solution
    public static int lev2(String str1, String str2) {

        if (str1 == null || str2 == null) return 0;

        int str1Len = str1.length();
        int str2Len = str2.length();

        int[][] levMemosBU = new int[str1Len+1][str2Len+1];
        for (int i=0; i <= str1Len; i++) levMemosBU[i][0] = i;
        for (int i=0; i <= str2Len; i++) levMemosBU[0][i] = i;

        for (int i=0; i < str1Len; i++) {
            for (int j=0; j < str2Len; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    levMemosBU[i+1][j+1] = levMemosBU[i][j];
                } else {
                    levMemosBU[i+1][j+1] = 1 + min(levMemosBU[i][j+1], levMemosBU[i+1][j], levMemosBU[i][j]);
                }
            }
        }

        return levMemosBU[str1Len][str2Len];
    }
}
