package calculator;

import calculator.arithmetic.*;
import calculator.arithmetic.doub.*;
import calculator.arithmetic.integer.*;
import calculator.exceptions.IllegalOperatorException;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator<T> extends Calculator<T> {

    private final List<Operator> operators = new ArrayList<>();

    public ArithmeticCalculator(Class<T> type) {
        super();
        if (type == Integer.class) {
            operators.add(new AddOperatorInt());
            operators.add(new SubtractOperatorInt());
            operators.add(new MultiplyOperatorInt());
            operators.add(new DivideOperatorInt());
            operators.add(new ModOperatorInt());
        } else if (type == Double.class) {
            operators.add(new AddOperatorDouble());
            operators.add(new SubtractOperatorDouble());
            operators.add(new MultiplyOperatorDouble());
            operators.add(new DivideOperatorDouble());
            operators.add(new ModOperatorDouble());
        }
    }

    public T calculate(T a, T b, char operator) throws IllegalOperatorException {
        OperatorType operatorType = OperatorType.getOperatorType(operator);
        for (Operator<T> op : operators) {
            if (op.getOperatorType() == operatorType) {
                return op.operate(a, b);
            }
        }
        throw new IllegalOperatorException(operator);
    }
}
