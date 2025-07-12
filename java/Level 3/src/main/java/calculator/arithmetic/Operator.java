package calculator.arithmetic;

public interface Operator {
    Double operate(Double a, Double b);

    OperatorType getOperatorType();
}
