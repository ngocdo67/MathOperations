package mathops;

public class MathOperationExecution {
    public static void main(String[] args) {
        MathOperation mathOperation = new MathOperationImplementation();
        System.out.println ("Expression: " + args[0]);
        try {
            System.out.println ("Result: " + mathOperation.calculate(args[0]));
        } catch (Exception e) {
            System.out.println ("Error: " + e.getMessage());
        }
    }
}
