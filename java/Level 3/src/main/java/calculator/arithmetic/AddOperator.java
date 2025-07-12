package calculator.arithmetic;

public class AddOperator implements Operator{
    @Override
    public Double operate(Double a, Double b) {
        return a + b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.ADD;
    }
}
