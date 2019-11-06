package eili.leet002;

import java.util.ArrayDeque;

/**
 * Problem #:  0020
 * Name:       Validate Parenthesis
 * Tags:       Stack
 * Difficulty: Easy
 * Techniques:
 * Learnings:
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class Leet0020 {

    public static void main(String[] args) {
        System.out.println("isValid ''="+isValid(""));
        System.out.println("isValid '[]'="+isValid("[]"));
        System.out.println("isValid '()'="+isValid("()"));
        System.out.println("isValid '{}'="+isValid("{}"));
        System.out.println("isValid '('="+isValid("("));
        System.out.println("isValid ')'="+isValid(")"));
        System.out.println("isValid '[)]'="+isValid("[)]"));
        System.out.println("isValid '[()]'="+isValid("[()]"));
        System.out.println("isValid '[(a)]'="+isValid("[(a)]"));
        System.out.println("isValid '[({)]'="+isValid("[({)]"));
        System.out.println("isValid '[({)]}'="+isValid("[({)]}"));
        System.out.println("isValid '[({)}]'="+isValid("[({)}]"));
        System.out.println("isValid '[({})]'="+isValid("[({})]"));
    }

    // Optimization:
    // We can return false when the stack is
    // This is because there is not enough characters to close all open parens
    public static boolean isValid(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i=0; i < chars.length; i++) {
            char c = chars[i];
            int charsRemaining = chars.length - i;
            if (charsRemaining < stack.size()) {
                return false;
            } else if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                if (stack.size() == 0) {
                    return false;
                } else if (c == ']' && stack.pop() != '[') {
                    return false;
                } else if (c == '}' && stack.pop() != '{') {
                    return false;
                } else if (c == ')' && stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

}
