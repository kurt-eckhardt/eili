package eili.leet.leet000;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  44
 * Name:       Wildcard Matching
 * Tags:       String, RegEx
 * BigO:       O(s+p)
 * Difficulty: Hard
 * Techniques: Recursion, Dynamic Programming, Top Down, Memoization
 * Learnings:  This is similar to Levinstein distance.  We need to figure out how
 *             to break down the problem into smaller pieces.  The key insights are:
 *             1) Match all fixed characters and single '?'.
 *             2) Match wildcard using one of three cases (see comments in code)
 *             3) Use memoization to improve performance
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 */
public class Leet0044 {

    static Map<String, Boolean> memos = new HashMap<>();
    public static boolean isMatch(String s, String p) {

        String memoKey = s + "_+_" + p;
        if (memos.containsKey(memoKey)) {
            return memos.get(memoKey);
        }

        // remove all prefix characters that must match
        while (!s.isEmpty() && !p.isEmpty() && p.charAt(0) != '*') {
            if (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0)) {
                s = s.substring(1);
                p = p.substring(1);
            } else {
                memos.put(memoKey, false);
                return false;
            }
        }

        // remove all suffix characters that must match
        while (!s.isEmpty() && !p.isEmpty() && p.charAt(p.length()-1) != '*') {
            if (p.charAt(p.length()-1) == '?' || p.charAt(p.length()-1) == s.charAt(s.length()-1)) {
                s = s.substring(0,s.length()-1);
                p = p.substring(0,p.length()-1);
            } else {
                memos.put(memoKey, false);
                return false;
            }
        }

        // now test the wildcard.  There are 3 cases
        // 1) Match zero (keep string same, remove wildcard from pattern)
        // 2) Match one  (remove char from string, remove wildcard from pattern)
        // 3) Match two+ (remove char from string, keep wildcard in pattern)

        if (p.isEmpty() && s.isEmpty()) {
            memos.put(memoKey, true);
            return true;
        } else if (p.isEmpty()) {
            memos.put(memoKey, false);
            return false;
        } else {
            boolean result = false;
            if (s.isEmpty()) {
                if (p.startsWith("*")) result |= isMatch(s, p.substring(1));
            } else {
                if (p.startsWith("*")) {
                    result |= isMatch(s, p.substring(1)) ||
                              isMatch(s.substring(1), p.substring(1)) ||
                              isMatch(s.substring(1), p);
                }
            }

            memos.put(memoKey, result);
            return result;
        }
    }

    public static void main(String[] args) {
//        System.out.println(false == isMatch("aa",""));
//        System.out.println(false == isMatch("aa","a"));
//        System.out.println(true  == isMatch("aa","aa"));
//        System.out.println(true  == isMatch("aa","?a"));
//        System.out.println(true  == isMatch("aa","??"));
//        System.out.println(true  == isMatch("aa","a?"));
//        System.out.println(false == isMatch("aa","???"));
//        System.out.println(true  == isMatch("aa","*"));
//        System.out.println(true  == isMatch("aa","*a"));
//        System.out.println(true  == isMatch("aa","**?*a**"));
//        System.out.println(true  == isMatch("axa","**?*a"));
//        System.out.println("Pass 12=["+!isMatch("mississippi","m??*ss*?i*pi")+"]");
//        System.out.println("Pass 13=["+isMatch("babaabbbbbaaaaabbaababbaaaaaaabbaabaabbbabbaabbbbb","*ba**bbbb")+"]");
        System.out.println("Pass 14=["+!isMatch("abaabaaaabbabbaaabaabababbaabaabbabaaaaabababbababaabbabaabbbbaabbbbbbbabaaabbaaaaabbaabbbaaaaabbbabb","ab*aaba**abbaaaa**b*b****aa***a*b**ba*a**ba*baaa*b*ab*")+"]");
    }
}
