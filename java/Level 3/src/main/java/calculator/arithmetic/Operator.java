package calculator.arithmetic;

public interface Operator {
    int operate(int a, int b);

    OperatorType getOperatorType();
}
