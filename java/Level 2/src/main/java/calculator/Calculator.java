package calculator;

public class Calculator {
    private Integer results = 0;

    public Integer calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                results = a + b;
                break;
            case '-':
                results = a - b;
                break;
            case '*':
                results = a * b;
                break;
            case '/':
                if(b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                results = a / b;
                break;
            default:
                results = 0;
        }
        return results;
    }

}
