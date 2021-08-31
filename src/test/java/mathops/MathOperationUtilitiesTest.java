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
        System.out.println (Arrays.toString(MathOperationUtilities.infixToPostfix(inorder)));

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




}