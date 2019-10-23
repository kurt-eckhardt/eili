package eili.leet;

/**
 * Problem #:  0006
 * Name:
 * Tags:       Datastructures, Array, String
 * Difficulty: Medium
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows
 * like this:
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 *
 * P   I   N   5
 * A L S I G 4 6
 * Y A H R 1 3 7
 * P   I   2
 *
 * Learnings:
 * 1. Keep it simple.  Get the simple solution working before trying to optimize
 *    it or try to come up with a more elegant implementation.
 *
 * 2. Check your edge cases (numRows <= 0, 1, sLen-1, sLen, sLen+1)
 */
public class Leet0006 {

    public static void main(String[] args) {
        System.out.println("Solution for 1  x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",1));
        System.out.println("Solution for 2  x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",2));
        System.out.println("Solution for 3  x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",3));
        System.out.println("Solution for 4  x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",4));
        System.out.println("Solution for 13 x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",13));
        System.out.println("Solution for 14 x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",14));
        System.out.println("Solution for 15 x [PAYPALISHIRING]=" + solution("PAYPALISHIRING",15));
    }

    /**
     * P A Y P A L I S H I R I N G 1 2 3 4 5
     * 0 1 2 3 2 1 0 1 2 3 2 1 0 1 2 3 2 1 0
     *
     * p i n 5
     * a l s i g 4
     * y a h r 1 3
     * p i 2
     *
     * P   I   N   5
     * A L S I G 4
     * Y A H R 1 3
     * P   I   2
     */
    public static String solution(String s, int numRows) {

        if (numRows <= 1 || numRows >= s.length()) return s;

        int numChars = s.length();
        char[] chars = new char[numChars];
        int charIndx = 0;
        for (int row = 0; row < numRows; row++) {

            int numToAdd = 1;
            int rowValue = 0;
            for (int i=0; i < numChars; i++) {

                if (rowValue == row) {
                    chars[charIndx++] = s.charAt(i);
                }

                rowValue += numToAdd;
                if (rowValue == numRows-1) {
                    numToAdd=-1;
                }

                if (rowValue == 0) {
                    numToAdd=1;
                }
            }
        }

        return new String(chars);
    }
}
