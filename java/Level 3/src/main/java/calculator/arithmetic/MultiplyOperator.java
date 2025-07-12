package calculator.arithmetic;

public class MultiplyOperator implements Operator{
    @Override
    public Double operate(Double a, Double b) {
        return a * b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }
}
