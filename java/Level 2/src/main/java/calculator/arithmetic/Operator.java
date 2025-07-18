package calculator.arithmetic;

public interface Operator {
    Integer operate(int a, int b);

    char getOperator();
}
