package calculator;

import calculator.arithmetic.AddOperator;
import calculator.arithmetic.DivideOperator;
import calculator.arithmetic.MultiplyOperator;
import calculator.arithmetic.SubtractOperator;

public class ArithmeticCalculator extends Calculator<Integer> {


    public ArithmeticCalculator() {
        super();
    }

    public Integer calculate(int a, int b, char operator) {
        return switch (operator) {
            case '+' -> AddOperator.operate(a, b);
            case '-' -> SubtractOperator.operate(a, b);
            case '*' -> MultiplyOperator.operate(a, b);
            case '/' -> DivideOperator.operate(a, b);
            default -> 0;
        };
    }
}
