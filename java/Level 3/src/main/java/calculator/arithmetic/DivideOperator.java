package calculator.arithmetic;

public class DivideOperator implements Operator {

    @Override
    public int operate(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
            return 0;
        }
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.DIVIDE;
    }
}
