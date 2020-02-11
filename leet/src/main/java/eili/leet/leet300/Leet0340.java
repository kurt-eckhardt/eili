package eili.leet.leet300;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0340
 * Name:       Longest Substring With K Unique Characters
 * Tags:       Google
 * BigO:       Time O(N), Space O(1)
 * Difficulty: Hard (Medium)
 * Techniques: Two Pointers, HashMap
 * Learnings:  1) int lIdx = 0;
 *                int rIdx = 0;
 *             2) while (rIdx < chars.length())
 *
 * Problem Description:
 * Given a string, print longest possible substring that has exactly M unique characters.
 * If there are more than one substring of longest possible length, then print any one of them.
 *
 * Example #1:
 * "aabbcc", k = 1
 * Max substring can be any one from {"aa" , "bb" , "cc"}.
 *
 * "aabbcc", k = 2
 * Max substring can be any one from {"aabb" , "bbcc"}.
 *
 * "aabbcc", k = 3
 * There are substrings with exactly 3 unique characters
 * {"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 * Max is "aabbcc" with length 6.
 *
 * "aaabbb", k = 3
 * There are only two unique characters, thus show error message.
 */
public class Leet0340 {

    public static void main(String[] args) {
        System.out.println(longestSubstring("aabbcc", 3));
        System.out.println(longestSubstring("aaabbb", 3));
        System.out.println(longestSubstring("aabacbebebe", 3));
    }

    public static String longestSubstring(String chars, int k) {

        int lIdx = 0;
        int rIdx = 0;
        String longest = "";
        Map<Character, Integer> charCount = new HashMap<>();

        while (rIdx < chars.length()) {

            // Add characters until we can add no more
            while (rIdx < chars.length()) {
                int numUnique = charCount.keySet().size();
                if (numUnique < k) {
                    char charToAdd = chars.charAt(rIdx++);
                    charCount.put(charToAdd, charCount.getOrDefault(charToAdd, 0)+1);
                } else if (numUnique == k && charCount.containsKey(chars.charAt(rIdx))) {
                    char charToAdd = chars.charAt(rIdx++);
                    charCount.put(charToAdd, charCount.getOrDefault(charToAdd, 0)+1);
                } else {
                    break;
                }
            }

            // did we find a new maximum & do we have k unique characters?
            int length = rIdx - lIdx;
            if (charCount.size() == k && length >= longest.length()) {
                longest = chars.substring(lIdx, rIdx);
                System.out.println("Found longest candidate: " + longest);
            }

            // move left index forward until we can add a new unique character
            while (charCount.keySet().size() >= k && lIdx < rIdx) {
                char toRemove = chars.charAt(lIdx++);
                int count = charCount.get(toRemove);
                if (count == 1) {
                    charCount.remove(toRemove);
                } else {
                    charCount.put(toRemove, count - 1);
                }
            }
        }

        return longest;
    }
}
