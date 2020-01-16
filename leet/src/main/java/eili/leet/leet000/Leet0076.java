package eili.leet.leet000;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  76
 * Name:       Min Window Substring
 * Tags:       Favorite
 * BigO:       O(S) Time, O(T) Space
 * Difficulty: Hard
 * Techniques: Two Pointers, HashMap
 * Learnings:
 * Related:
 *
 * Given a string S and a string T,
 * find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Leet0076 {

    // Move left and right to first letter in 't'
    // Move right index until all letters in 't' are covered
    // Update min window size
    // Move left until we find a letter in 't'
    // If all letters are still covered, update min window size and move left per above
    // Else move right index until all letters in 't' are covered
    public static String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";

        int lIdx = 0;
        int rIdx = 0;
        String min = null;
        char[] schars  = s.toCharArray();
        Map<Character, Integer> tCharsFound = new HashMap<>();
        Map<Character, Integer> tCharCounts = new HashMap<>();
        for (Character tchar : t.toCharArray()) tCharCounts.put(tchar, tCharCounts.getOrDefault(tchar,0)+1);

        while (lIdx < s.length() && !tCharCounts.containsKey(schars[lIdx])) {
            lIdx++; rIdx++;
        }
        if (rIdx < s.length() && tCharCounts.containsKey(schars[rIdx])) tCharsFound.put(schars[rIdx], 1);

        while (rIdx < s.length()) {
             System.out.println("lIdx="+lIdx + " rIdx="+rIdx);

            if (allLettersFound(tCharsFound, tCharCounts)) {
                 System.out.print("All Letters Found: ");
                 for (Character tchar : tCharCounts.keySet()) System.out.print(tchar+"="+tCharsFound.getOrDefault(tchar, 0)+" ");
                 if (min == null || rIdx - lIdx < min.length()-1) {
                     System.out.println(" Setting min to ["+ s.substring(lIdx, rIdx+1)+"]");
                     min = s.substring(lIdx, rIdx+1);
                 } else {
                     System.out.println();
                 }
                tCharsFound.put(schars[lIdx], tCharsFound.get(schars[lIdx])-1);
                do { lIdx++; } while (lIdx < rIdx && !tCharCounts.containsKey(schars[lIdx]));
            } else {
                rIdx++;
                if (rIdx < s.length() && tCharCounts.containsKey(schars[rIdx])) {
                    tCharsFound.put(schars[rIdx], tCharsFound.getOrDefault(schars[rIdx], 0)+1);
                }
            }
        }

        return (min != null) ? min : "";
    }


    private static boolean allLettersFound(Map<Character, Integer> tCharsFound, Map<Character, Integer> tCharCounts) {
        for (Character tchar : tCharCounts.keySet()) {
            if (tCharsFound.getOrDefault(tchar, 0) < tCharCounts.get(tchar)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("Min Window=["+minWindow2("ADOBECODEBANCADOBECODEBANC", "DEBANCA")+"]");
    }



    public static String minWindow2(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";

        int lIdx = 0;
        int rIdx = 0;
        int minLIdx = 0;
        int minRIdx = 0;
        int found = 0;
        char[] schars = s.toCharArray();
        Map<Character, Integer> tCharCounts = new HashMap<>();
        for (Character tchar : t.toCharArray()) tCharCounts.put(tchar, tCharCounts.getOrDefault(tchar,0)-1);

        while (lIdx < schars.length && !tCharCounts.containsKey(schars[lIdx])) {
            lIdx++; rIdx++;
        }
        if (rIdx < schars.length && tCharCounts.containsKey(schars[rIdx])) {
            tCharCounts.put(schars[rIdx], tCharCounts.get(schars[rIdx])-1);
            if (tCharCounts.get(schars[rIdx]) == 0) found++;
        }

        while (rIdx < schars.length) {
            System.out.println("lIdx="+lIdx + " rIdx="+rIdx + " found="+found);

            if (found == t.length()) {
                System.out.print("All Letters Found: ");
                //for (Character tchar : tCharCounts.keySet()) System.out.print(tchar+"="+tCharsFound.getOrDefault(tchar, 0)+" ");
                if (rIdx - lIdx < minRIdx - minLIdx) {
//                    System.out.println(" Setting min to ["+ s.substring(lIdx, rIdx+1)+"]");
                    minRIdx = rIdx;
                    minLIdx = lIdx;
                }

                tCharCounts.put(schars[lIdx], tCharCounts.get(schars[lIdx])-1);
                if (tCharCounts.get(schars[lIdx]) < 0) found--;
                do { lIdx++; } while (lIdx < rIdx && !tCharCounts.containsKey(schars[lIdx]));
            } else {
                rIdx++;
                if (rIdx < schars.length && tCharCounts.containsKey(schars[rIdx])) {
                    tCharCounts.put(schars[rIdx], tCharCounts.getOrDefault(schars[rIdx], 0)+1);
                    if (tCharCounts.get(schars[rIdx]) == 0) found++;
                }
            }
        }

        return s.substring(minLIdx, minRIdx+1);
    }

}
