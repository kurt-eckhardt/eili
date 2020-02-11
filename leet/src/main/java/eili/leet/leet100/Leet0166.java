package eili.leet.leet100;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0166
 * Name:       Fraction to Recurring Decimal
 * Tags:       Math
 * BigO:       Time O(N), Space O(1)
 * Difficulty: Medium
 * Techniques:
 * Learnings:
 *
 * Problem Description:
 * Given two integers representing the numerator and denominator of a fraction, return the
 * fraction in string format.  If the fractional part is repeating, enclose the repeating
 * part in parentheses.
 *
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 *
 * Example 3:
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class Leet0166 {

    static String fractionToDecimal(int numer, int denom) {
        if (numer == 0) return "0";
        if (denom == 0) return "";
        return f2d(numer, denom);
    }

    static String f2d(long numerator, long denominator) {

        StringBuilder answer = new StringBuilder();
        Map<Long, Integer> seen = new HashMap<>();

        int iter   = 0;
        long numer = Math.abs(numerator);
        long denom = Math.abs(denominator);

        answer.append((numerator < 0 ^ denominator < 0) ? "-" : "");
        answer.append(numer / denom);
        long remainder = numer % denom;
        if (remainder != 0) answer.append(".");
        int decimalIdx = answer.length()-1;
        while (remainder != 0) {
            iter++;
            numer = (numer % denom);
            if (seen.containsKey(numer)) {
                answer.insert(seen.get(numer)+decimalIdx, "(");
                answer.append(")");
                break;
            }
            seen.put(numer, iter);
            numer = numer * 10;
            answer.append(numer / denom);
            remainder = numer % denom;
        }

        return answer.toString();
    }



    public static void main(String[] args) {
        System.out.println(fractionToDecimal(-473,-573));
//        System.out.println(fractionToDecimal(5,-11));
//        System.out.println(fractionToDecimal(-7,6));
//        System.out.println(fractionToDecimal(Integer.MAX_VALUE,Integer.MIN_VALUE));
//        System.out.println(fractionToDecimal(355,113));
//        System.out.println(fractionToDecimal(245850922,78256779));
    }



//    static String f2d_orig(long numerator, long denominator) {
//
//        StringBuilder answer = new StringBuilder();
//        Map<String, Integer> seen = new HashMap<>();
//
//        int iter   = 0;
//        long numer = Math.abs(numerator);
//        long denom = Math.abs(denominator);
//
//        answer.append((numerator < 0 ^ denominator < 0) ? "-" : "");
//        answer.append(numer/denom);
//        long remainder = numer % denom;
//        if (remainder != 0) answer.append(".");
//        while (remainder != 0) {
//            iter++;
//            numer = (numer % denom);
//            String memoKey = numer+"/"+denom;
//            if (seen.containsKey(memoKey)) {
//                answer.reverse();
//                answer.insert(iter-seen.get(memoKey), "(");
//                answer.reverse();
//                answer.append(")");
//                break;
//            }
//            seen.put(memoKey, iter);
//            numer = numer * 10;
//            answer.append(numer / denom);
//            remainder = numer % denom;
//        }
//
//        return answer.toString();
//    }


//    private void estimatePI() {
//        double bestErr = 1d;
//        for (int i=0; i < 100000; i++) {
//            double r = (double)n/(double)d;
//            double e = Math.abs(PI - r);
//            System.out.println(bestErr + "  " + e);
//            if (e < bestErr) {
//                System.out.println("iter="+i + " " + n + "/" + d); //+ " = "+fractionToDecimal(n,d));
//                bestErr = e;
//            }
//            if (r > PI) {
//                d++;
//            } else {
//                n++;
//            }
//        }
//    }
}
