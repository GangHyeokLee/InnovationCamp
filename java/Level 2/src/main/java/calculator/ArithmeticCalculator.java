package calculator;

public class ArithmeticCalculator extends Calculator<Integer> {

    public ArithmeticCalculator() {
        super();
    }

    public Integer calculate(int a, int b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                yield a / b;
            }
            default -> 0;
        };
    }
}
