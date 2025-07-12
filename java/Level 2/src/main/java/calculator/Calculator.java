package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final List<Integer> results;
    private final List<Double> circles;

    public Calculator() {
        this.results = new ArrayList<>();
        this.circles = new ArrayList<>();
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

    public List<Integer> getResults() {
        return results;
    }

    public void setResult(Integer n) {
        results.add(n);
    }

    public void removeResult(){
        results.remove(0);
    }

    public void inquiryResults(){
        results.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public double calculateCircleArea(int radius) {
        return radius * radius * Math.PI;
    }

    public List<Double> getCircles() {
        return circles;
    }

    public void setCircles(double circle) {
        this.circles.add(circle);
    }

}
