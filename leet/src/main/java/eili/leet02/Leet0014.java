package eili.leet02;

/**
 * Problem #:  0014
 * Name:       Longest Common Prefix
 * Tags:       Strings
 * Difficulty: Easy
 * Techniques: Greedy
 * Learnings:  ?
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Note: All given inputs are in lowercase letters a-z.
 */
public class Leet0014 {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"flower","flow","flight"}));
    }

    public static String solution(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        int minStrLen = Integer.MAX_VALUE;
        for (int i=0; i < strs.length; i++) {
            minStrLen = Math.min(minStrLen, strs[i].length());
        }

        String longest = "";
        for (int i=0; i < minStrLen; i++) {
            char aChar = strs[0].charAt(i);
            for (int j=1; j < strs.length; j++) {
                if (strs[j].charAt(i) != aChar) {
                    return longest;
                }
            }
            longest += aChar;
        }

        return longest;
    }
}
