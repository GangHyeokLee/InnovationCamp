package calculator.arithmetic.integer;

import calculator.arithmetic.Operator;
import calculator.arithmetic.OperatorType;

public class DivideOperatorInt implements Operator<Integer> {

    @Override
    public Integer operate(Integer a, Integer b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
            return null;
        }
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.DIVIDE;
    }
}
