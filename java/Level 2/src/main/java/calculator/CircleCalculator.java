package calculator;

public class CircleCalculator extends Calculator<Double> {

    public CircleCalculator() {
        super();
    }

    public Double calculate(int radius) {
        return radius * radius * Math.PI;
    }
}
