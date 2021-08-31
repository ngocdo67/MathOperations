package mathops;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MathOperationImplementationTest {
    private MathOperationImplementation mathOperationImplementation;

    @BeforeMethod
    public void setUp() {
        mathOperationImplementation = new MathOperationImplementation();
    }

    @Test
    public void shouldConvertToTreeSimple() throws Exception{
        String input = "(1 + 1) * 2";
        BinaryTree expectedTree = buildExpectedSimpleTree();

        assertEquals(mathOperationImplementation.convertOperationToTree(input), expectedTree);
    }

    @Test
    public void shouldConvertToTree() throws Exception{
        String input = "(( 15 / (7 - (1 + 1))) * -3) -(2 + (1 + 1))";
        BinaryTree expectedTree = buildExpectedTreeCaseOne();

        assertEquals(mathOperationImplementation.convertOperationToTree(input), expectedTree);
    }

    @Test
    public void shouldCalculateCorrectly() throws Exception{
        String input = "(( 15 / (7 - (1 + 1))) * -3) -(2 + (1 + 1))";

        assertEquals(mathOperationImplementation.calculate(input), -13);
    }

    @Test
    public void shouldCalculateNegativeExpression() throws Exception{
        assertEquals(mathOperationImplementation.calculate("-1"), -1);
        assertEquals(mathOperationImplementation.calculate("- 100"), -100);
        assertEquals(mathOperationImplementation.calculate("- (1 + 100)"), -101);
        assertEquals(mathOperationImplementation.calculate("- 1 * -80"), 80);
    }

    @Test
    public void shouldCalculateOneOperand() throws Exception{
        assertEquals(mathOperationImplementation.calculate("1000"), 1000);
        assertEquals(mathOperationImplementation.calculate("(1000)"), 1000);
    }

    @Test (expectedExceptions = Exception.class)
    public void shouldErrorOnInvalidOperation() throws Exception {
        mathOperationImplementation.calculate("1 / 0");
    }

    @Test(expectedExceptions = Exception.class)
    public void shouldNotCalculateInvalidExpression() throws Exception{
        System.out.println(mathOperationImplementation.calculate("5 15"));
    }

    private BinaryTree buildExpectedSimpleTree () {
        BinaryTree onePlusOne = initTree("+", "1", "1");
        return initTree("*", onePlusOne, "2");
    }

    private BinaryTree buildExpectedTreeCaseOne () {
        BinaryTree onePlusOne = initTree("+", "1", "1");
        BinaryTree sevenMinus = initTree("-", "7", onePlusOne);
        BinaryTree fifteenDivide = initTree("/", "15", sevenMinus);
        BinaryTree minusThree = initTreeWithOnlyRight("-", "3");
        BinaryTree multiply = initTree("*", fifteenDivide, minusThree);

        BinaryTree newOnePlusOne = initTree("+", "1", "1");
        BinaryTree twoPlus = initTree("+", "2", newOnePlusOne);

        return initTree("-", multiply, twoPlus);
    }

    private BinaryTree initTreeWithOnlyRight (String op, String right) {
        BinaryTree res = new BinaryTree(op);
        res.setRight(new BinaryTree(right));
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
