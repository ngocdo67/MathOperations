package mathops;

import java.util.Stack;

public class MathOperationImplementation implements MathOperation {

    @Override
    public BinaryTree convertOperationToTree(String operation) {
        String operators = "+-x/";
        int number = 0;
        Stack<BinaryTree> operandHolder = new Stack<>();
        char[] operations = operation.toCharArray();
        for (int i=0; i < operations.length; i++) {
            char current = operations[i];
            if (operators.indexOf(current) != -1) {

            }
        }
        return null;
    }

    @Override
    public int calculate(String operation) {
        return 0;
    }

}
