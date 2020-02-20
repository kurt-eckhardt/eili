package eili.leet.leet000;

/**
 *
 *
 * Given a string containing just the characters '(' and ')', find the
 * length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class Leet0032 {

    // 0123456789ABC      opens closed
    // ((()()((())()      8     5
    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int lff = longestFromFront(chars);
        int lfb = longestFromBack(chars);
        return Math.max(lff, lfb);
    }

    private static int longestFromFront(char[] chars) {
        int longest = 0;
        int ocCount = 0;
        int startI  = 0;
        for (int i=0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ocCount++;
            } else {
                ocCount--;
                if (ocCount == 0) {
                    longest = Math.max(i-startI+1, longest);
                } else if (ocCount < 0) {
                    startI  = i+1;
                    ocCount = 0;
                }
            }
        }

        return longest;
    }

    private static int longestFromBack(char[] chars) {
        int longest = 0;
        int ocCount = 0;
        int startI  = chars.length-1;
        for (int i=chars.length-1; i >=0; i--) {
            if (chars[i] == ')') {
                ocCount++;
            } else {
                ocCount--;
                if (ocCount == 0) {
                    longest = Math.max(startI-i+1, longest);
                } else if (ocCount < 0) {
                    startI  = i-1;
                    ocCount = 0;
                }
            }
        }

        return longest;
    }


    // 0123456789ABC
    // ((()()((())()
    // i	num open	current 	longest
    // 0	1		    0           0
    // 1	2		    0           0
    // 2	3		    0 	        0
    // 3    2           1           0
    // 4    3           1           0
    // 5    2           2           0
    // 6    3           2           0
    // 7    4           2
    // 8    5           2
    // 9    4           3
    // A    3           4
    // B    4           4
    // C    3           5

    // 0123456789A
    // ()((()()())
    // i	num open	current 	longest
    // 0	1		    0
    // 1	0		    1		    1***
    // 2	1		    0
    // 3	2		    0
    // 4	3		    0
    // 5	2		    1
    // 6	3		    1
    // 7	2	    	2
    // 8	3	    	2
    // 9	2	    	3
    // A	1	    	4
//    public int longestValidParentheses(String s) {
//
//        char[] chars = s.toCharArray();
//        int longest = 0;
//        int current = 0;
//        int numOpen = 0;
//
//        for (int i=0; i < chars.length; i++) {
//            if (chars[i] == '(') {
//                numOpen++;
//            } else {
//                numOpen--;
//                if (numOpen < 0) {
//                    numOpen = 0;
//                } else if (numOpen == 0) {
//                    longest += current + 2;
//                    current = 0;
//                } else if (numOpen > 0) {
//                    current += 2;
//                }
//            }
//        }
//
//        return Math.max(current, longest);
//    }

}
