package mathops;

import java.util.*;

public final class MathOperationUtilities {

    public static String[] infixToPostfix(String[] infix) throws Exception {
        String allOperators = "+-*/";
        Queue<String> operands = new LinkedList<>();
        Stack<String> operators = new Stack<>();
        for (String token : infix) {
            if (allOperators.contains(token)) {
                while (!operators.isEmpty() && !operators.peek().equals("(") && !hasLowerPrecedence(operators.peek().charAt(0), token.charAt(0))) {
                    operands.add(operators.pop());
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
            } else {
                throw new Exception("Invalid expression: " + Arrays.toString(infix));
            }
        }
        while (!operators.isEmpty()) {
            operands.add(operators.pop());
        }
        return operands.toArray(new String[0]);
    }

    private static boolean hasLowerPrecedence (char first, char second) {
        String lowPrecedence = "+-";
        return lowPrecedence.indexOf(first) > -1 && lowPrecedence.indexOf(second) < 0;
    }

    public static String[] tokenizeExpression(String input) {
        List<String> tokens = new ArrayList<>();
        char[] inputChars = input.toCharArray();
        Integer num = 0;
        for (char inputChar : inputChars) {
            if (Character.isDigit(inputChar)) {
                int digit = inputChar - '0';
                if (num != null) {
                    num = num * 10 + digit;
                } else {
                    num = digit;
                }
            } else {
                if (num != null) {
                    tokens.add(Integer.toString(num));
                    num = null;
                }
                if (inputChar != ' ') {
                    tokens.add(Character.toString(inputChar));
                }
            }
        }
        return tokens.toArray(new String[0]);
    }
}
