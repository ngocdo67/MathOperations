package mathops;

public interface MathOperation {

    BinaryTree convertOperationToTree (String operation) throws Exception;
    int calculate (String operation) throws Exception;
}
