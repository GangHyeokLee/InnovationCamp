package calculator.arithmetic;

public class ModOperator implements Operator {
    @Override
    public Double operate(Double a, Double b) {
        return a % b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MOD;
    }
}
