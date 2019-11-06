package eili.leet002;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0013
 * Name:       Roman to Integer
 * Tags:
 * Difficulty: Easy
 * Techniques: Break-It-Down
 * Learnings:  ?
 *
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 * Input: "III"
 * Output: 3
 *
 * Example 2:
 * Input: "IV"
 * Output: 4
 *
 * Example 3:
 * Input: "IX"
 * Output: 9
 *
 * Example 4:
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Example 5:
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class Leet0013 {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIXVI"));
    }

    static Map<String, Integer> map = new HashMap<>();
    static {
        int[]    numvals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numeral = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < numeral.length; i++) {
            map.put(numeral[i], numvals[i]);
        }
    }


    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return map.get(s.charAt(0)+"");
        }

        int val = 0;
        int idx = 1;
        while (idx < s.length()) {
            String numeralUsingOneLetter = "" + s.charAt(idx-1);
            String numeralUsingTwoLetter = "" + s.charAt(idx-1) +  s.charAt(idx);
            if (map.containsKey(numeralUsingTwoLetter)) {
                val += map.get(numeralUsingTwoLetter);
                idx+=2;
            } else if (map.containsKey(numeralUsingOneLetter)) {
                val += map.get(numeralUsingTwoLetter);
                idx+=1;
            } else {
                return -1; // not valid
            }
        }

        if (idx == s.length() && map.containsKey(""+s.charAt(idx-1))) {
            val += map.get(""+s.charAt(idx-1));
        }

        return val;
    }

}

