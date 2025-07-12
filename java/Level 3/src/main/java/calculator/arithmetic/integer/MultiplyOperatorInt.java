package calculator.arithmetic.integer;


import calculator.arithmetic.Operator;
import calculator.arithmetic.OperatorType;

public class MultiplyOperatorInt implements Operator<Integer> {
    @Override
    public Integer operate(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.MULTIPLY;
    }
}
