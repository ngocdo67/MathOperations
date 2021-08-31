package mathops;

import java.util.Stack;

public class MathOperationImplementation implements MathOperation {

    @Override
    public BinaryTree convertOperationToTree(String operation) throws Exception {
        String[] postfix = MathOperationUtilities.infixToPostfix(MathOperationUtilities.tokenizeExpression(operation));
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
        if (operandHolder.size() != 1) {
            throw new Exception("Invalid operation: " + operation);
        }
        return operandHolder.peek();
    }

    @Override
    public int calculate(String operation) throws Exception {
        return calculate(convertOperationToTree(operation));
    }

    private int calculate (BinaryTree tree) throws Exception {
        if (tree.getLeft() == null && tree.getRight() == null) {
            return Integer.parseInt(tree.getValue());
        }
        if (tree.getLeft() == null) {
            if ("-".equals(tree.getValue())) {
                return - calculate(tree.getRight());
            } else {
                throw new Exception("Invalid operation");
            }
        }
        return switch (tree.getValue()) {
            case "+" -> calculate(tree.getRight()) + calculate(tree.getLeft());
            case "-" -> calculate(tree.getLeft()) - calculate(tree.getRight());
            case "*" -> calculate(tree.getLeft()) * calculate(tree.getRight());
            case "/" -> calculate(tree.getLeft()) / calculate(tree.getRight());
            default -> 0;
        };
    }

}
