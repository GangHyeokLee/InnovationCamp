package calculator.arithmetic;


public class MultiplyOperator implements Operator{
    @Override
    public Integer operate(int a, int b) {
        return a * b;
    }

    @Override
    public char getOperator() {
        return '*';
    }

}
