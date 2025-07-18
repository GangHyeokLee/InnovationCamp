package calculator.arithmetic;

public class DivideOperator implements Operator {
    @Override
    public char getOperator() {
        return '/';
    }

    @Override
    public Integer operate(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
            return null;
        }
    }
}
