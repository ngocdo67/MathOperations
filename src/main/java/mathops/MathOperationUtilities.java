package mathops;

import java.util.*;

public final class MathOperationUtilities {
    private static final String ALL_OPERATORS = "+-*/()";
    private static final String CALCULATION_OPERATORS = "+-*/";

    /**
     * Convert an infix expression displayed in an array of string to a postfix expression.
     * For example -(1 + 1) * 2 + 6 is converted to be 1 1 + - 2 * 6 +
     *
     * @param infix
     * @return
     * @throws Exception is thrown if expression is invalid
     */
    public static String[] infixToPostfix(String[] infix) throws Exception {
        Queue<String> operands = new LinkedList<>();
        Stack<String> operators = new Stack<>();
        String prev = null;
        boolean negativeNum = false;
        for (String token : infix) {
            if (CALCULATION_OPERATORS.contains(token)) {
                negativeNum = processOperatorTokenAndCheckIfTokenIsNegative(operands, operators, prev, negativeNum, token);
            } else if (token.equals("(")) {
                operators.push("(");
            } else if (token.equals(")")) {
                processExpressionBetweenParentheses(infix, operands, operators);
            } else if (Character.isDigit(token.charAt(0))){
                negativeNum = processNumericTokenAndResetNegativeVariable(operands, operators, negativeNum, token);
            } else {
                throw new Exception("Invalid expression: " + getListForErrorMessage(infix));
            }
            prev = token;
        }
        while (!operators.isEmpty()) {
            if ("(".equals(operators.peek())) {
                throw new Exception("Invalid expression: " + getListForErrorMessage(infix));
            }
            operands.add(operators.pop());
        }
        return operands.toArray(new String[0]);
    }

    private static boolean processNumericTokenAndResetNegativeVariable(Queue<String> operands, Stack<String> operators, boolean negativeNum, String token) {
        operands.add(token);
        if (negativeNum && operators.peek().equals("-")) {
            operands.add(operators.pop());
            negativeNum = false;
        }
        return negativeNum;
    }

    private static void processExpressionBetweenParentheses(String[] infix, Queue<String> operands, Stack<String> operators) throws Exception {
        while (!operators.isEmpty() && !operators.peek().equals("(")) {
            operands.add(operators.pop());
        }
        if (operators.isEmpty() || !operators.peek().equals("(")) {
            throw new Exception("Invalid expression: " + getListForErrorMessage(infix));
        }
        operators.pop();
    }

    private static boolean processOperatorTokenAndCheckIfTokenIsNegative(Queue<String> operands, Stack<String> operators, String prev, boolean negativeNum, String token) {
        if ("-".equals(token) && (prev == null || "(".equals(prev) || CALCULATION_OPERATORS.contains(prev))) {
            operands.add("");
            negativeNum = true;
        } else {
            while (!operators.isEmpty() && !operators.peek().equals("(") && !hasLowerPrecedence(operators.peek().charAt(0), token.charAt(0))) {
                operands.add(operators.pop());
            }
        }
        operators.push(token);
        return negativeNum;
    }

    private static boolean hasLowerPrecedence (char first, char second) {
        String lowPrecedence = "+-";
        return lowPrecedence.indexOf(first) > -1 && lowPrecedence.indexOf(second) < 0;
    }

    /**
     * Convert an expression in String type to a list of tokens.
     * A string "15 + 1 - 3 * (1 + 2)" will be a list of 15, +, 1, -, 3, *, (, 1, +, 2, )
     *
     * @param input
     * @return
     * @throws Exception if the expression contains invalid characters
     */
    public static String[] tokenizeExpression(String input) throws Exception {
        List<String> tokens = new ArrayList<>();
        char[] inputChars = input.toCharArray();
        Integer num = null;
        for (char inputChar : inputChars) {
            if (Character.isDigit(inputChar)) {
                num = addDigitToNumber(num, inputChar);
            } else {
                num = addFullNumber(tokens, num);
                if (ALL_OPERATORS.indexOf(inputChar) > -1){
                    addOperator(tokens, inputChar);
                } else if (inputChar != ' '){
                    throw new Exception("Invalid input: " + input);
                }
            }
        }
        if (num != null) {
            tokens.add(Integer.toString(num));
        }
        return tokens.toArray(new String[0]);
    }

    private static void addOperator(List<String> tokens, char inputChar) {
        if (inputChar != ' ') {
            tokens.add(Character.toString(inputChar));
        }
    }

    private static Integer addFullNumber(List<String> tokens, Integer num) {
        if (num != null) {
            tokens.add(Integer.toString(num));
            num = null;
        }
        return num;
    }

    private static Integer addDigitToNumber(Integer num, char inputChar) {
        int digit = inputChar - '0';
        if (num != null) {
            num = num * 10 + digit;
        } else {
            num = digit;
        }
        return num;
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

    private static String getListForErrorMessage (String[] tokens) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String token : tokens) {
            messageBuilder.append(token).append(' ');
        }
        return messageBuilder.toString();
    }
}
