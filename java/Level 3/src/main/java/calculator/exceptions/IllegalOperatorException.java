package calculator.exceptions;

public class IllegalOperatorException extends Exception {
    public IllegalOperatorException(char operator) {
        super("지원하지 않는 연산자입니다: " + operator);
    }
}
