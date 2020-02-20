package eili.leet.leet600;

/**
 * Problem Description:
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * Example 1:
 * Input: "aba"
 * Output: True

 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Example 3: "ececabbacec" True
 */
public class Leet0680 {

    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        return validPalindrome(chars, 0, chars.length-1, true);
    }

    public boolean validPalindrome(char[] chars, int lIdx, int rIdx, boolean canSkip) {
        while (lIdx < rIdx) {
            if (chars[lIdx] == chars[rIdx]) {
                lIdx++;
                rIdx--;
            } else if (canSkip == true) {
                return validPalindrome(chars, lIdx, rIdx-1, false) ||
                        validPalindrome(chars, lIdx+1, rIdx, false);
            } else {
                return false;
            }
        }

        return true;
    }
}
