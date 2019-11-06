package eili.leet001;

/**
 * Problem #:  0010
 * Name:       Regular Expression Matching
 * Tags:
 * Difficulty: Hard
 * Techniques: Break-It-Down
 * Learnings:  ?
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

    public static void main(String[] args) {
        System.out.println("match[,]="+solution("",""));
        System.out.println("match[,a]="+solution("","a"));
        System.out.println("match[aa,]="+solution("aa",""));
        System.out.println("match[aa,a]="+solution("aa","a"));
        System.out.println("match[aa,aa]="+solution("aa","aa"));
        System.out.println("match[aa,a.]="+solution("aa","a."));
        System.out.println("match[aa,a*]="+solution("aa","a*"));
        System.out.println("match[aaa,a*]="+solution("aaa","a*"));
        System.out.println("match[aaab,a*b]="+solution("aaab","a*b"));
        System.out.println("match[aaab,a*.]="+solution("aaab","a*."));
        System.out.println("match[ab,.*]="+solution("ab",".*"));
        System.out.println("match[caab,c*a*b]="+solution("caab","c*a*b"));
        System.out.println("match[aab,c*a*b]="+solution("aab","c*a*b"));
        System.out.println("match[mississippi,mis*is*p*.]="+solution("mississippi","mis*is*p*."));
        System.out.println("match[,*]="+solution("","*"));
        System.out.println("match[,**]="+solution("","**"));
        System.out.println("match[,.*]="+solution("",".*"));
        System.out.println("match[,..*]="+solution("","..*"));
        System.out.println("match[a,*]="+solution("a","*"));
        System.out.println("match[a,**]="+solution("a","**"));
        System.out.println("match[a,.*]="+solution("a",".*"));
        System.out.println("match[a,..*]="+solution("a","..*"));
        System.out.println("match[a,.**]="+solution("a",".**"));
        System.out.println("match[a,.**]="+solution("ab",".*c"));
    }

    public static boolean solution(String str, String pat) {

        if (str == null || pat == null)     return false;
        if (str.isEmpty() && pat.isEmpty()) return false;

        char[] schars = str.toCharArray();
        char[] pchars = pat.toCharArray();
        char wildchar = (char)0; // previous pattern character

        int sIdx=0;
        int pIdx=0;
        while (sIdx < schars.length && pIdx < pchars.length) {
            char schar = schars[sIdx];
            char pchar = pchars[pIdx];
            if (pchar == '*') {
                if (wildchar == '.') {
                    return true;
                } else if (schar == wildchar) {
                    sIdx++;
                } else {
                    wildchar = pchar;
                    pIdx++;
                }
            } else if (pchar == '.' || schar == pchar) {
                wildchar = pchar;
                pIdx++;
                sIdx++;
            } else if (pIdx < pchars.length-1 && pchars[pIdx+1]=='*') {
                wildchar = pchars[pIdx+1];
                pIdx += 2;
            } else {
                return false;
            }
        }

        return (sIdx >= schars.length);
    }
}
