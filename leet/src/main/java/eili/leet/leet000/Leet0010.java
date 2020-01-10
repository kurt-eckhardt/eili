package eili.leet.leet000;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #:  0010
 * Name:       Regular Expression Matching
 * Tags:
 * Difficulty: Hard
 * Techniques: Break-It-Down, Memoization
 * Learnings:  This problem sucked!
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated n time, b 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class Leet0010 {

    public static Map<String, Boolean> memos = new HashMap<String, Boolean>();

    public static boolean isMatch(String s, String p) {
        memos.clear();
        return isMatch(s, p, null);
    }

    public static boolean isMatch(String s, String p, Character prev) {

        boolean result;
        String memoKey = s + "_:_"+ p + "_:_" + prev;
        if (memos.containsKey(memoKey)) {
            return memos.get(memoKey);
        } else if (s == null || p == null) {
            result = false;
        } else if (s.isEmpty() && p.isEmpty()) {
            result = true;
        } else if (p.length() > 1 && p.charAt(1) == '*') {
            if (prev == null) prev = p.charAt(0);
            if (!s.isEmpty()) {
                boolean isFirstCharMatch = prev == '.' || prev == s.charAt(0);
                result = isMatch(s, p.substring(2), null)
                        || (isFirstCharMatch && isMatch(s.substring(1), p.substring(2), null))
                        || (isFirstCharMatch && isMatch(s.substring(1), p, prev));
            } else {
                result = isMatch(s, p.substring(2), null);
            }
        } else if (!p.isEmpty() && !s.isEmpty()) {
            boolean isFirstCharMatch = p.charAt(0) == '.' || p.charAt(0) == s.charAt(0);
            result = isFirstCharMatch && isMatch(s.substring(1), p.substring(1), null);
        } else {
            result = false;
        }

        memos.put(memoKey, result);
        return result;
    }



//    public static boolean isMatch(String s, String p) {
//
////        System.out.println("s="+s + " p=" + p);
//
//        Character prev = null;
//        while (!s.isEmpty() && !p.isEmpty() && !p.startsWith("*")) {
//            prev = p.charAt(0);
//            if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
//                s = s.substring(1);
//                p = p.substring(1);
//            } else {
//                return false;
//            }
//        }
//
//        if (s.isEmpty() && p.isEmpty()) {
//            return true;
//        } else if (p.isEmpty()) {
//            return false;
//        }
//
//        return isMatchZeroOneOrMoreOfPrev(prev, s, p);
//    }
//
//
//    public static boolean isMatchZeroOneOrMoreOfPrev(Character prev, String s, String p) {
////        System.out.println("s="+s + " p=" + p + " prev="+ prev);
//        if (prev == null) {
//            return false;
//        } else if (s.isEmpty() && p.isEmpty()) {
//            return true;
//        } else if (s.isEmpty() && !p.startsWith("*")) {
//            return false;
//        } else if (s.isEmpty() && p.startsWith("*")) {
//            return isMatchZeroOneOrMoreOfPrev(prev, s, p.substring(1));
//        } else if (prev == '.' || s.charAt(0) == prev) {
//            return isMatch(s.substring(1), p.substring(1)) || isMatchZeroOneOrMoreOfPrev(prev, s.substring(1), p);
//        } else {
//            return isMatch(s, p.substring(1)); // zero
//        }
//    }
//
//    public static boolean isMatch(String str, String pat) {
//
//        if (str == null || pat == null)     return false;
//        if (str.isEmpty() && pat.isEmpty()) return false;
//
//        char[] schars = str.toCharArray();
//        char[] pchars = pat.toCharArray();
//        char wildchar = (char)0; // previous pattern character
//
//        int sIdx=0;
//        int pIdx=0;
//        while (sIdx < schars.length && pIdx < pchars.length) {
//            char schar = schars[sIdx];
//            char pchar = pchars[pIdx];
//            if (pchar == '*') {
//                if (wildchar == '.') {
//                    return true;
//                } else if (schar == wildchar) {
//                    sIdx++;
//                } else {
//                    wildchar = pchar;
//                    pIdx++;
//                }
//            } else if (pchar == '.' || schar == pchar) {
//                wildchar = pchar;
//                pIdx++;
//                sIdx++;
//            } else if (pIdx < pchars.length-1 && pchars[pIdx+1]=='*') {
//                wildchar = pchars[pIdx+1];
//                pIdx += 2;
//            } else {
//                return false;
//            }
//        }
//
//        return (sIdx >= schars.length);
//    }


    public static void main(String[] args) {
//        System.out.println("match[,]="+isMatch("",""));
//        System.out.println("match[,a]="+isMatch("","a"));
//        System.out.println("match[aa,]="+isMatch("aa",""));
//        System.out.println("match[aa,a]="+isMatch("aa","a"));
//        System.out.println("match[aa,aa]="+isMatch("aa","aa"));
//        System.out.println("match[aa,a.]="+isMatch("aa","a."));
//        System.out.println("match[aa,a*]="+isMatch("aa","a*"));
//        System.out.println("match[aaa,a*]="+isMatch("aaa","a*"));
//        System.out.println("match[aaab,a*b]="+isMatch("aaab","a*b"));
//        System.out.println("match[aaab,a*.]="+isMatch("aaab","a*."));
//        System.out.println("match[ab,.*]="+isMatch("ab",".*"));
//        System.out.println("match[caab,c*a*b]="+isMatch("caab","c*a*b"));
//        System.out.println("match[aab,c*a*b]="+isMatch("aab","c*a*b"));
//        System.out.println("match[mississippi,mis*is*p*.]="+isMatch("mississippi","mis*is*p*."));
//        System.out.println("match[,*]="+isMatch("","*"));
//        System.out.println("match[,**]="+isMatch("","**"));
//        System.out.println("match[,.*]="+isMatch("",".*"));
//        System.out.println("match[,..*]="+isMatch("","..*"));
//        System.out.println("match[a,*]="+isMatch("a","*"));
//        System.out.println("match[a,**]="+isMatch("a","**"));
//        System.out.println("match[a,.*]="+isMatch("a",".*"));
//        System.out.println("match[a,..*]="+isMatch("a","..*"));
//        System.out.println("match[a,.**]="+isMatch("a",".**"));
//        System.out.println("match[ab,.*c]="+isMatch("ab",".*c"));
//        System.out.println("match[ab,.*c]="+isMatch("ab",".*c"));
        System.out.println("match[aaaaaaaaaaaaab,a*a*a*a*a*a*a*a*a*a*c]="+isMatch("aaaaaaaaaaaaab","a*a*a*a*a*a*a*a*a*a*c"));
    }
}


