package eili.leet.leet000;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #:  0017
 * Name:       Letter Combinations of a Phone Number
 * Tags:       Math, Subset, Combinations, Array, Bitset
 * Difficulty: Medium
 * Techniques: Brute-Force
 * Learnings:  ?
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent.  The mappings are:
 * 2=abc, 3=def, 4=ghi, 5=jkl, 6=mno, 7=pqrs, 8=tuv, 9=wxyz
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Leet0017 {
    public static void main(String[] args) {
        List<String> combos = solution("234");
        System.out.println(combos);
    }

    public static List<String> solution(String sDigits) {

        List<String> combos = new ArrayList<>();

        // create digit -> character lookup
        // note 7 and 9 have 4 characters instead of 3
        char[][] lookup = {
                {},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},
                {'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
        };

        // convert sDigits to numbers once for speed
        int numDigits = sDigits.length();
        int[] digits  = new int[numDigits];
        for (int i=0; i < numDigits; i++) {
            digits[i] = sDigits.charAt(i) - '0';
        }

        // bitset will track which character to select from the lookup array
        // stop when we have iterated over all combinations.
        int[] bitset = new int[numDigits+1];
        char[] combo = new char[numDigits];
        while (bitset[numDigits] == 0) {

            for (int digit=0; digit<numDigits; digit++) {
                combo[digit] = lookup[digits[digit]][bitset[digit]]; // 2-9, 0-3
            }
            combos.add(new String(combo));

            // advance to next pattern in bitset
            for (int bit=0; bit < bitset.length; bit++) {
                bitset[bit]++;
                if ((bitset[bit] > 2 && (digits[bit] != 7 && digits[bit] != 9)) || bitset[bit] > 3) {
                    bitset[bit] = 0;
                } else {
                    break;
                }
            }
        }

        return combos;
    }

}
