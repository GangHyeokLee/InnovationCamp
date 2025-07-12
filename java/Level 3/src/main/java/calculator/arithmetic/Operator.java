package calculator.arithmetic;

public interface Operator<T> {
    T operate(T a, T b);

    OperatorType getOperatorType();
}
