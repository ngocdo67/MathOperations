package mathops;

import java.util.ArrayList;
import java.util.List;

public final class MathOperationUtilities {

    public static String[] infixToPostfix(String[] infix) {

        return null;
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
