package eili.goog;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class ExprEval {

    public static void main(String[] args) {
        String expr = "( + 5 6 ( * 2 3 4( + 3 4) 7 8) 123 456)";
        System.out.println(new Expr(expr).eval());
    }


    public static class Expr {

        String op;
        StringTokenizer st;
        boolean hasOpenParen;
        ArrayDeque<Integer> resultStack = new ArrayDeque<>();

        public Expr(String e) {
            this.st = new StringTokenizer(e.trim(), " ()+*-/", true);
            String first = "";
            while (st.hasMoreTokens()) {
                first = st.nextToken();
                if (!first.equals(" ")) {
                    break;
                }
            }

            if (first.equals("(")) {
                hasOpenParen = true;
                parseOp();
            } else {
                hasOpenParen = false;
                op = first;
            }
        }

        private Expr(StringTokenizer st) {
            this.st = st;
            this.hasOpenParen = true;
            parseOp();

        }

        private void parseOp() {
            while (st.hasMoreTokens()) {
                op = st.nextToken();
                if (!op.equals(" ")) {
                    break;
                }
            }
        }

        public Integer eval() {
            while (st.hasMoreTokens()) {
                String aToken = st.nextToken();
                System.out.println("op=" + op + " aToken="+aToken + " rs="+resultStack);
                if (aToken.equals("(")) {
                    resultStack.push(new Expr(st).eval());
                } else if (aToken.equals(")")) {
                    if (hasOpenParen) {
                        reduce();
                        return resultStack.pop();
                    } else {
                        throw new RuntimeException("closing paren without open");
                    }
                } else if (aToken.equals(" ")) {
                    continue;
                } else {
                    resultStack.push(Integer.parseInt(aToken));
                }

                reduce();
            }

            reduce();
            return resultStack.pop();
        }


        private void reduce() {
            if (resultStack.size() == 2) {
                Integer num2 = resultStack.pop();
                Integer num1 = resultStack.pop();
                if (op.equals("+")) {
                    resultStack.push(num1 + num2);
                } else if (op.equals("-")) {
                    resultStack.push(num1 - num2);
                } else if (op.equals("*")) {
                    resultStack.push(num1 * num2);
                } else if (op.equals("/")) {
                    resultStack.push(num1 / num2);
                }
            }
        }
    }
}
