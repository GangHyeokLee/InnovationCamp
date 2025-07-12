package calculator.arithmetic;

public class SubtractOperator implements Operator{
    @Override
    public Double operate(Double a, Double b) {
        return a - b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.SUBTRACT;
    }
}
