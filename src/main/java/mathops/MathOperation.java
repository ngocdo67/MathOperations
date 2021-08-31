package mathops;

public interface MathOperation {

    /**
     * Converts a string expression of math operation to a binary tree
     * @param operation
     * @return
     * @throws Exception
     */
    BinaryTree convertOperationToTree (String operation) throws Exception;

    /**
     * Calculates a math operation by first converting it to a binary tree.
     * @param operation
     * @return
     * @throws Exception
     */
    int calculate (String operation) throws Exception;
}
