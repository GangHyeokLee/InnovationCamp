package calculator.arithmetic.doub;


import calculator.arithmetic.Operator;
import calculator.arithmetic.OperatorType;

public class MultiplyOperatorDouble implements Operator<Double> {
    @Override
    public Double operate(Double a, Double b) {
        return a * b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }
}
