package mathops;

import java.util.*;

public final class MathOperationUtilities {
    private static final String ALL_OPERATORS = "+-*/()";
    private static final String CALCULATION_OPERATORS = "+-*/";

    public static String[] infixToPostfix(String[] infix) throws Exception {
        Queue<String> operands = new LinkedList<>();
        Stack<String> operators = new Stack<>();
        String prev = null;
        boolean negativeNum = false;
        for (String token : infix) {
            if (CALCULATION_OPERATORS.contains(token)) {
                if ("-".equals(token) && (prev == null || "(".equals(prev) || CALCULATION_OPERATORS.contains(prev))) {
                    operands.add("");
                    negativeNum = true;
                } else {
                    while (!operators.isEmpty() && !operators.peek().equals("(") && !hasLowerPrecedence(operators.peek().charAt(0), token.charAt(0))) {
                        operands.add(operators.pop());
                    }
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push("(");
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    operands.add(operators.pop());
                }
                if (operators.isEmpty() || !operators.peek().equals("(")) {
                    throw new Exception("Invalid expression: " + Arrays.toString(infix));
                }
                operators.pop();
            } else if (Character.isDigit(token.charAt(0))){
                operands.add(token);
                if (negativeNum && operators.peek().equals("-")) {
                    operands.add(operators.pop());
                    negativeNum = false;
                }
            } else {
                throw new Exception("Invalid expression: " + Arrays.toString(infix));
            }
            prev = token;
        }
        while (!operators.isEmpty()) {
            if ("(".equals(operators.peek())) {
                throw new Exception("Invalid expression: " + Arrays.toString(infix));
            }
            operands.add(operators.pop());
        }
        return operands.toArray(new String[0]);
    }

    private static boolean hasLowerPrecedence (char first, char second) {
        String lowPrecedence = "+-";
        return lowPrecedence.indexOf(first) > -1 && lowPrecedence.indexOf(second) < 0;
    }

    public static String[] tokenizeExpression(String input) throws Exception {
        List<String> tokens = new ArrayList<>();
        char[] inputChars = input.toCharArray();
        Integer num = null;
        for (char inputChar : inputChars) {
            if (Character.isDigit(inputChar)) {
                int digit = inputChar - '0';
                if (num != null) {
                    num = num * 10 + digit;
                } else {
                    num = digit;
                }
            } else if (ALL_OPERATORS.indexOf(inputChar) > -1){
                if (num != null) {
                    tokens.add(Integer.toString(num));
                    num = null;
                }
                if (inputChar != ' ') {
                    tokens.add(Character.toString(inputChar));
                }
            } else if (inputChar != ' '){
                throw new Exception("Invalid input: " + input);
            }
        }
        if (num != null) {
            tokens.add(Integer.toString(num));
        }
        return tokens.toArray(new String[0]);
    }

    public static boolean isOperand (String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOperator (String s) {
        return s.length() == 1 && "+-*/()".indexOf(s.charAt(0)) > -1;
    }
}
