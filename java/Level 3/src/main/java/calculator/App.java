package calculator;

import calculator.exceptions.IllegalOperatorException;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IllegalOperatorException {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        ArithmeticCalculator<Integer> arithmeticInt = new ArithmeticCalculator<>(Integer.class);
        ArithmeticCalculator<Double> arithmeticIntDoub = new ArithmeticCalculator<>(Double.class);
        CircleCalculator circular = new CircleCalculator();

        while (flag) {

            System.out.print("명령어를 입력하거나 첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            switch (input) {
                case "exit" -> flag = false;
                case "remove" -> {
                    while(true){
                        System.out.print("어떤 타입의 기록을 지우려 하시나요?: 1. Integer, 2. Double, 3. Circle");
                        input = sc.nextLine();
                        try{
                            int t = Integer.parseInt(input);

                            switch (t) {
                                case 1:
                                    arithmeticInt.removeResult();
                                    break;
                                case 2:
                                    arithmeticIntDoub.removeResult();
                                    break;
                                case 3:
                                    circular.removeResult();
                                    break;
                            }
                            break;
                        }catch (NumberFormatException e){
                            System.out.println("번호만 입력하세요.");
                        }
                    }
                }
                case "inquiry" -> {
                    arithmeticInt.inquiryResults();
                    arithmeticIntDoub.inquiryResults();
                    circular.inquiryResults();
                }
                case "circle" -> {
                    System.out.print("반지름을 입력하세요: ");
                    String r = sc.nextLine();
                    try {
                        int radius = Integer.parseInt(r);
                        double circle = circular.calculate(radius);
                        circular.setResult(circle);
                        System.out.println("결과: " + circle);
                    } catch (NumberFormatException e) {
                        System.out.print("숫자만 입력하세요");
                    }
                }
                default -> {
                    // 최초 입력 숫자 타입 감지
                    boolean isInt = true;
                    int firstNumInt = 0, secondNumInt = 0;
                    double firstNumDouble = 0, secondNumDouble = 0;
                    try {
                        firstNumInt = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        try {
                            firstNumDouble = Double.parseDouble(input);
                            isInt = false;
                        } catch (NumberFormatException ex) {
                            System.out.println("숫자만 입력하세요.");
                            break;
                        }
                    }

                    System.out.print("두 번째 숫자를 입력하세요: ");
                    input = sc.nextLine();
                    if (isInt) {
                        try {
                            secondNumInt = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println("숫자만 입력하세요.");
                            break;
                        }
                    } else {
                        try {
                            secondNumDouble = Double.parseDouble(input);
                        } catch (NumberFormatException e) {
                            System.out.println("숫자만 입력하세요.");
                            break;
                        }
                    }

                    System.out.print("사칙연산 기호를 입력하세요: ");
                    char op = sc.nextLine().charAt(0);

                    if (isInt) {
                        int result = arithmeticInt.calculate(firstNumInt, secondNumInt, op);
                        arithmeticInt.setResult(result);
                        System.out.println("결과: " + result);
                    } else {
                        double result = arithmeticIntDoub.calculate(firstNumDouble, secondNumDouble, op);
                        arithmeticIntDoub.setResult(result);
                        System.out.println("결과: " + result);
                    }

                    System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
                    System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
                    System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                }
            }
        }

        sc.close();
    }
}
