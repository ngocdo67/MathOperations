package mathops;

public class MathOperationExecution {
    public static void main(String[] args) {
        MathOperation mathOperation = new MathOperationImplementation();
        for (String argument : args) {
            System.out.println ("Expression: " + argument);
            try {
                System.out.println ("Result: " + mathOperation.calculate(argument));
            } catch (Exception e) {
                System.out.println ("Error: " + e.getMessage());
            }
        }
    }
}
