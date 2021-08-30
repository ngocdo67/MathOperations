package mathops;

import java.util.Stack;

public class MathOperationImplementation implements MathOperation {

    @Override
    public BinaryTree convertOperationToTree(String operation) throws Exception {
        String[] postorder = MathOperationUtilities.infixToPostfix(MathOperationUtilities.tokenizeExpression(operation));
        String operators = "+-x/";
        int number = 0;
        Stack<BinaryTree> operandHolder = new Stack<>();
        
        return null;
    }

    @Override
    public int calculate(String operation) {
        return 0;
    }

}
