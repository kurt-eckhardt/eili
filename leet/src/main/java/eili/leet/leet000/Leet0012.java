package eili.leet.leet000;

/**
 * Problem #:  0012
 * Name:       Int to Roman
 * Tags:       Math, Arrays
 * Difficulty: Medium
 * Techniques: Break-It-Down
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
 *
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *
 * Input: 3
 * Output: "III"
 * Example 2:
 *
 * Input: 4
 * Output: "IV"
 * Example 3:
 *
 * Input: 9
 * Output: "IX"
 * Example 4:
 *
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 *
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Learnings:
 * 1) Don't spend too much time on a fancy solution - a direct, working solution is better than nothing.
 * 2) Roman numerals don't follow the typical conversion math (num / 10, num % 10).
 */
public class Leet0012 {

    // Not general, but works per problem description.
    public static String leet1(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }


    // This is a nice solution.  It uses an array instead of many if-else statements to lookup digits
    public static String leet2(int num) {
        int[] values  = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }

        return sb.toString();
    }


    public static String intToRomanIter(int val) {
        String roman = "";
        while (val > 0) {
            if (val < 4) {
                roman += "I";
                val = val - 1;
            } else if (val < 5) {
                roman += "IV";
                val = val - 4;
            } else if (val < 9) {
                roman += "V";
                val = val - 5;
            } else if (val < 10) {
                roman += "IX";
                val = val - 9;
            } else if (val < 40) {
                roman += "X";
                val = val - 10;
            } else if (val < 50) {
                roman += "XL";
                val = val - 40;
            } else if (val < 90) {
                roman += "L";
                val = val - 50;
            } else if (val < 100) {
                roman += "XC";
                val = val - 90;
            } else if (val < 400) {
                roman += "C";
                val = val - 100;
            } else if (val < 500) {
                roman += "CD";
                val = val - 400;
            } else if (val < 900) {
                roman += "D";
                val = val - 500;
            } else if (val < 1000) {
                roman += "CM";
                val = val - 900;
            } else {
                roman += "M";
                val = val - 1000;
            }
        }

        return roman;
    }


    public static String intToRoman(int val) {
        if (val < 0) {
            return "-" + intToRoman(val * -1);
        } else if (val == 0) {
            return "";
        } else if (val < 4) {
            return "I" + intToRoman(val - 1);
        } else if (val < 5) {
            return "IV";
        } else if (val < 9) {
            return "V" + intToRoman(val - 5);
        } else if (val < 10) {
            return "IX";
        } else if (val < 40) {
            return "X" + intToRoman(val - 10);
        } else if (val < 50) {
            return "XL" + intToRoman(val - 40);
        } else if (val < 90) {
            return "L" + intToRoman(val - 50);
        } else if (val < 100) {
            return "XC" + intToRoman(val - 90);
        } else if (val < 400) {
            return "C" + intToRoman(val - 100);
        } else if (val < 500) {
            return "CD" + intToRoman(val - 400);
        } else if (val < 900) {
            return "D" + intToRoman(val - 500);
        } else if (val < 1000) {
            return "CM" + intToRoman(val - 900);
        } else {
            return "M" + intToRoman(val - 1000);
        }
    }


    public static void main(String[] args) {
        for (int i=0; i <= 100; i++) {
            System.out.println(i+"="+intToRoman(i) + " iter="+intToRomanIter(i));
        }
        System.out.println("249="+intToRoman(249) + " iter="+intToRomanIter(249));
        System.out.println("449="+intToRoman(449) + " iter="+intToRomanIter(449));
        System.out.println("489="+intToRoman(489) + " iter="+intToRomanIter(489));
        System.out.println("499="+intToRoman(499) + " iter="+intToRomanIter(499));
        System.out.println("2459="+intToRoman(2459) + " iter="+intToRomanIter(2459));
    }

}
