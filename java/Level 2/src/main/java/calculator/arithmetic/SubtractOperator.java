package calculator.arithmetic;

public class SubtractOperator implements Operator{
    @Override
    public int operate(int a, int b) {
        return a - b;
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
