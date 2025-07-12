package calculator.arithmetic;

import calculator.exceptions.IllegalOperatorException;

public enum OperatorType {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), MOD('%');

    private final char operator;

    OperatorType(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }

    public static OperatorType getOperatorType(char operator) throws IllegalOperatorException {
        for (OperatorType type : OperatorType.values()) {
            if (type.getOperator() == operator) {
                return type;
            }
        }
        throw new IllegalOperatorException(operator);
    }
}
