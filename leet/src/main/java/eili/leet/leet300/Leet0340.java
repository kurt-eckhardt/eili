package eili.leet.leet300;

import java.util.HashMap;
import java.util.Map;

/**
 * Hard (Medium)
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
