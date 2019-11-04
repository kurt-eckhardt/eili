package eili.leet03;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Leet0022 {

    public static void main(String[] args) {
        printAllCombinations2(13);
    }


    public static List<String> allValidParens(int n) {
        List<String> results = new ArrayList<>();
        BigInteger bi = BigInteger.ONE.shiftLeft(n*2); // num combinations
        char[] parens = new char[n*2];
        do {
            int numOpenParens=0;
            for (int bit=0; bit < parens.length; bit++) {
                if (bi.testBit(bit)) {
                    parens[bit] = '(';
                    numOpenParens++;
                } else {
                    parens[bit] = ')';
                    numOpenParens--;
                }

                if (numOpenParens < 0) {
                    break;
                }
            }

            // Only add this parens pattern if it is legal
            if (numOpenParens == 0) {
                results.add(new String(parens));
            }

            bi = bi.subtract(BigInteger.ONE);
        } while (!bi.equals(BigInteger.ZERO));

        return results;
    }


    public static List<String> printAllCombinations2(int n) {
        List<String> results = new ArrayList<>();
        long bitset   = (1 << (n*2)) - 1;
        char[] parens = new char[n*2];
        do {
            int numOpenParens=0;
            for (int bit=0; bit < parens.length; bit++) {
                if ((bitset >> bit & 0x1) == 1) {
                    parens[bit] = '(';
                    numOpenParens++;
                } else {
                    parens[bit] = ')';
                    numOpenParens--;
                }

                // Only add this parens pattern if it is legal
                if (numOpenParens < 0) {
                    break;
                }
            }

            if (numOpenParens == 0) {
                results.add(new String(parens));
            }

            bitset--;
        } while (bitset >= 0);

        return results;
    }


//    public static boolean isValidParens(char[] chars) {
//        ;
//        for (int i=0; i < chars.length; i++) {
//            if (chars[i] == '(') openCount++; else openCount--;
//            if (openCount < 0) return false;
//        }
//        return true;
//    }

}