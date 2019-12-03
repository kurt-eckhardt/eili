package eili.goog;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class ExprEval2 {



    public static void main(String[] args) {
        System.out.println(eval("( + 5 6 ( * 2 3 4( + 3 4) 7 8) 123 456)"));
        System.out.println(eval("( - 5 2)"));
        System.out.println(eval("(/ 4 (* 8 ( - 5 2 (* 10 3))))"));
    }

    private static String eval(String expr) {
        ArrayDeque<String> opStack = new ArrayDeque<>();
        ArrayDeque<String> values  = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(expr, " ()+*-/", true);

        while (st.hasMoreTokens()) {
            String aToken = st.nextToken();
            switch (aToken) {
                case " ": continue;
                case "(": values.push("(");  break;
                case "+": opStack.push("+"); break;
                case "*": opStack.push("*"); break;
                case "-": opStack.push("-"); break;
                case "/": opStack.push("/"); break;
                case ")": reduce(opStack, values); break;
                default: values.push(aToken);
            }
        }

        return values.pop();

    }


    private static void reduce(ArrayDeque<String> opStack, ArrayDeque<String> values) {

        if (opStack.isEmpty() || values.isEmpty()) return;
        String opType  = opStack.pop();
        Integer result = Integer.parseInt(values.pop());

        while (!values.isEmpty()) {
            String aValue = values.pop();
            if (aValue.equals("(")) break;
            switch (opType) {
                case "+": result += Integer.parseInt(aValue); break;
                case "*": result *= Integer.parseInt(aValue); break;
                case "-": result -= Integer.parseInt(aValue); break;
                case "/": result /= Integer.parseInt(aValue); break;
            }
        }

        values.push(Integer.toString(result));
    }
}
