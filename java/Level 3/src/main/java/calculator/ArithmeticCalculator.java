package calculator;

import calculator.arithmetic.*;
import calculator.exceptions.IllegalOperatorException;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator extends Calculator<Integer> {

    private final List<Operator> operators = new ArrayList<>();

    public ArithmeticCalculator() {
        super();
        operators.add(new AddOperator());
        operators.add(new SubtractOperator());
        operators.add(new MultiplyOperator());
        operators.add(new DivideOperator());
        operators.add(new ModOperator());
    }

    public Integer calculate(int a, int b, char operator) throws IllegalOperatorException {
        OperatorType operatorType = OperatorType.getOperatorType(operator);
        for(Operator op : operators) {
            if(op.getOperatorType() == operatorType) {
                return op.operate(a, b);
            }
        }
        return 0;
    }
}
