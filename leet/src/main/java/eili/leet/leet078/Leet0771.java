package eili.leet.leet078;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem #:  0771
 * Name:       Jewels and Stones
 * Tags:       HashSet
 * BigO:       O(J+S) Time,  O(1) Space
 * Difficulty: Easy
 * Techniques:
 * Learnings:
 *
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
 * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
 * so "a" is considered a different type of stone from "A".
 *
 * Note:
 * S and J will consist of letters and have length at most 50.
 * The characters in J are distinct.
 *
 *
 * Example 1:
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 *
 * Example 2:
 * Input: J = "z", S = "ZZ"
 * Output: 0
 */
public class Leet0771 {

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }

    public static int numJewelsInStones(String J, String S) {

        if (J == null || S == null || J.isEmpty() || S.isEmpty()) return 0;

        Set<Character> jewels = new HashSet<>();
        for (char c : J.toCharArray()) {
            jewels.add(c);
        }

        int numJewels = 0;
        for (char s : S.toCharArray()) {
            if (jewels.contains(s)) numJewels++;
        }

        return numJewels;
    }
}
