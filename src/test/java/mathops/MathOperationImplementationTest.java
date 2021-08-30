package mathops;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MathOperationImplementationTest {
    MathOperationImplementation mathOperationImplementation;

    @BeforeMethod
    public void setUp() {
        mathOperationImplementation = new MathOperationImplementation();
    }

    @Test
    public void shouldConvertToTree() {
        String input = "(( 15 : (7 - (1 + 1))) x -3) -(2 + (1 + 1))";
        BinaryTree expectedTree = buildExpectedTreeCaseOne();

        assertEquals(mathOperationImplementation.convertOperationToTree(input), expectedTree);
    }

    @Test
    public void shouldCalculateCorrectly() {
        String input = "(( 15 / (7 - (1 + 1))) x -3) -(2 + (1 + 1))";

        assertEquals(mathOperationImplementation.calculate(input), -13);
    }

    private BinaryTree buildExpectedTreeCaseOne () {
        BinaryTree onePlusOne = initTree("+", "1", "1");
        BinaryTree sevenMinus = initTree("-", "7", onePlusOne);
        BinaryTree fifteenDivide = initTree("/", "15", sevenMinus);
        BinaryTree minusThree = initTreeWithOnlyRight("-", "3");
        BinaryTree multiply = initTree("x", fifteenDivide, minusThree);

        BinaryTree newOnePlusOne = initTree("+", "1", "1");
        BinaryTree twoPlus = initTree("+", "2", newOnePlusOne);

        return initTree("-", multiply, twoPlus);
    }

    private BinaryTree initTreeWithOnlyRight (String op, String right) {
        BinaryTree res = new BinaryTree(op);
        res.setRight(new BinaryTree(right));
        return res;
    }

    private BinaryTree initTreeWithOnlyLeft (String op, String left) {
        BinaryTree res = new BinaryTree(op);
        res.setLeft(new BinaryTree(left));
        return res;
    }

    private BinaryTree initTree (String op, BinaryTree left, String right) {
        BinaryTree res = new BinaryTree(op);
        res.setLeft(left);
        res.setRight(new BinaryTree(right));
        return res;
    }

    private BinaryTree initTree (String op, String left, BinaryTree right) {
        BinaryTree res = new BinaryTree(op);
        res.setLeft(new BinaryTree(left));
        res.setRight(right);
        return res;
    }

    private BinaryTree initTree (String op, BinaryTree left, BinaryTree right) {
        BinaryTree res = new BinaryTree(op);
        res.setLeft(left);
        res.setRight(right);
        return res;
    }
    private BinaryTree initTree(String op, String numOne, String numTwo) {
        BinaryTree res = new BinaryTree(op);
        res.setLeft(new BinaryTree(numOne));
        res.setRight(new BinaryTree(numTwo));
        return res;
    }
}
