package calculator.arithmetic;


public class MultiplyOperator implements Operator{
    @Override
    public int operate(int a, int b) {
        return a * b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }
}
