package eili.leet.leet400;

/**
 * Problem #:  0482
 * Name:       License Key Formatting
 * Tags:       String, Array, Reverse
 * BigO:       O(N) time, O(N) space
 * Difficulty: Easy
 * Techniques: Work Backwards
 * Learnings:  Inserting
 *
 *
 * you are given a license key represented as a string S which consists only alphanumeric character and dashes.
 * The string is separated into N+1 groups by N dashes.
 *
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
 * except for the first group which could be shorter than K, but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted
 * to uppercase.
 *
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 * Example 1:
 * Input: S = "5F3Z-2e-9-w", K = 4
 * Output: "5F3Z-2E9W"
 *
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 */
public class Leet0482 {

    public static void main(String[] args) {
        String s = "5F3Z-2e-9-w";
        for (int i=s.length()-1; i > 0; i--) {
            System.out.println(licenseKeyFormatting(s, i));
        }
    }

    public static String licenseKeyFormatting(String S, int K) {
        int groupLen = 0;
        StringBuilder formattedSB = new StringBuilder();
        char[] chars = S.toCharArray();
        for (int i=chars.length-1; i >= 0; i--) {
            if (chars[i] != '-') {
                if (groupLen == K) {
                    groupLen = 0;
                    formattedSB.append("-");
                }
                formattedSB.append(Character.toUpperCase(chars[i]));
                groupLen++;
            }
        }

        return formattedSB.reverse().toString();
    }
}
