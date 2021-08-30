package mathops;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathOperationUtilitiesTest {

    @Test
    public void testInfixToPostfix() {
        String inorder = "3 + 5 x 6 - 1";
        String postorder = "3 5 6 x + 1 -";
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }

    @Test
    public void testInfixToPostfixWithParentheses() {
        String inorder = "3 + 5 x (6 - 1)";
        String postorder = "3 5 6 1 - x +";
        assertEquals(MathOperationUtilities.infixToPostfix(inorder), postorder);
    }
}