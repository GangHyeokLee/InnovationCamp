package calculator.arithmetic.doub;

import calculator.arithmetic.Operator;
import calculator.arithmetic.OperatorType;

public class DivideOperatorDouble implements Operator<Double> {

    @Override
    public Double operate(Double a, Double b) {
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
