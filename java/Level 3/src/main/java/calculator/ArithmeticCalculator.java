package calculator;

import calculator.arithmetic.*;
import calculator.exceptions.IllegalOperatorException;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator extends Calculator<Double> {

    private final List<Operator> operators = new ArrayList<>();

    public ArithmeticCalculator() {
        super();
        operators.add(new AddOperator());
        operators.add(new SubtractOperator());
        operators.add(new MultiplyOperator());
        operators.add(new DivideOperator());
        operators.add(new ModOperator());

    }

    public Double calculate(Double a, Double b, char operator) throws IllegalOperatorException {
        OperatorType operatorType = OperatorType.getOperatorType(operator);
        for (Operator op : operators) {
            if (op.getOperatorType() == operatorType) {
                return op.operate(a, b);
            }
        }
        throw new IllegalOperatorException(operator);
    }

    public void printGreaterThan(double num){
        this.getResults().stream().filter(x -> x > num).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}
