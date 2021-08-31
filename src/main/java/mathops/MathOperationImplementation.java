package mathops;

import java.util.Arrays;
import java.util.Stack;

public class MathOperationImplementation implements MathOperation {

    @Override
    public BinaryTree convertOperationToTree(String operation) throws Exception {
        String[] postfix = MathOperationUtilities.infixToPostfix(MathOperationUtilities.tokenizeExpression(operation));
        String operators = "+-x/";
        int number = 0;
        System.out.println (Arrays.toString(postfix));
        Stack<BinaryTree> operandHolder = new Stack<>();
        for (String token : postfix) {
            if (MathOperationUtilities.isOperand(token)) {
                operandHolder.push(new BinaryTree(token));
            } else if (MathOperationUtilities.isOperator(token)) {
                if (operandHolder.size() < 2) {
                    throw new Exception("Invalid input: " + operation);
                }
                BinaryTree right = operandHolder.pop();
                if (right.getValue().isEmpty()) {
                    right = null;
                }
                BinaryTree left = operandHolder.pop();
                if (left.getValue().isEmpty()) {
                    left = null;
                }
                BinaryTree newTree = new BinaryTree(token, left, right);
                operandHolder.push(newTree);
            }
        }
        return operandHolder.peek();
    }

    @Override
    public int calculate(String operation) {
        return 0;
    }

}
