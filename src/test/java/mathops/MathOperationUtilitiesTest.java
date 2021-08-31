package mathops;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class MathOperationUtilitiesTest {

    @Test
    public void testInfixToPostfix() throws Exception{
        String[] inorder = new String[] {"3", "+", "5", "*", "6", "-",  "1"};
        String[] postorder = new String[] {"3", "5", "6", "*", "+", "1", "-"};
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void testInfixToPostfixWithParentheses() throws Exception{
        String[] inorder = new String[] {"3", "+", "5", "*", "(", "6", "-", "1", ")"};
        String[] postorder = new String[] {"3", "5", "6", "1", "-", "*",  "+"};
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void testInfixToPostfixWithParenthesesAndNeg() throws Exception{
        String[] inorder = new String[] {"3", "+", "-", "5", "*", "(", "6", "-", "1", ")"};
        String[] postorder = new String[] {"3", "", "5", "-", "6", "1", "-", "*",  "+"};
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void testInfixToPostfixWithParenthesesAndNegAfterParenthesis() throws Exception{
        String[] inorder = new String[] {"3", "+", "5", "*", "(", "-", "6", "-", "1", ")"};
        String[] postorder = new String[] {"3", "5", "", "6", "-", "1", "-", "*",  "+"};
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void testInfixToPostfixWithParenthesesAndNegBeforeParenthesis() throws Exception{
        String[] inorder = new String[] {"-", "(", "6", "-", "1", ")"};
        String[] postorder = new String[] {"", "6", "1", "-", "-"};
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void tokenizeExpression () {
        String input = "3 + 15 * (16 - (1 + 2))";
        String[] outputs = new String [] {"3", "+", "15", "*", "(", "16", "-", "(", "1", "+", "2", ")", ")"};
        assertEquals(MathOperationUtilities.tokenizeExpression(input), outputs);
    }

    @Test
    public void testValidOperand() {
        String[] validOperands = new String[] {"1", "15", "1351", "0"};
        for (String validOperand : validOperands) {
            assertTrue(MathOperationUtilities.isOperand(validOperand));
        }
    }

    @Test
    public void testInvalidOperand() {
        String[] validOperands = new String[] {"+", "ab", "  ", "*", "1a", "ab1"};
        for (String invalidOperand : validOperands) {
            assertFalse(MathOperationUtilities.isOperand(invalidOperand));
        }
    }

    @Test
    public void testValidOperators() {
        String[] validOperators = new String[] {"+", "-", "*", "/"};
        for (String validOperator : validOperators) {
            assertTrue(MathOperationUtilities.isOperator(validOperator));
        }
    }

    @Test
    public void testInvalidOperators() {
        String[] invalidOperators = new String[] {"++", "12345", "0", "    ", "a+", "1-", "-1"};
        for (String invalidOperator : invalidOperators) {
            assertFalse(MathOperationUtilities.isOperator(invalidOperator));
        }
    }

}