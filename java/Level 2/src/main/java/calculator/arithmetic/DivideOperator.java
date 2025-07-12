package calculator.arithmetic;

public class DivideOperator {
    public static int operate(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
            return 0;
        }
    }
}
