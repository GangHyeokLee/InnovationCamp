package calculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        ArithmeticCalculator arithmetic = new ArithmeticCalculator();
        CircleCalculator circular = new CircleCalculator();

        while (flag) {

            System.out.print("명령어를 입력하거나 첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            switch (input) {
                case "exit" -> flag = false;
                case "remove" -> arithmetic.removeResult();
                case "inquiry" -> {
                    arithmetic.inquiryResults();
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
                    int firstNum;
                    int secondNum;
                    try {
                        firstNum = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자만 입력하세요.");
                        break;
                    }

                    try {
                        System.out.print("두 번째 숫자를 입력하세요: ");
                        input = sc.nextLine();
                        secondNum = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자만 입력하세요.");
                        break;
                    }

                    System.out.print("사칙연산 기호를 입력하세요: ");

                    Integer result = arithmetic.calculate(firstNum, secondNum, sc.nextLine().charAt(0));
                    if (result != null) {
                        arithmetic.setResult(result);
                        System.out.println("결과: " + result);
                    }

                    System.out.println();
                    System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
                    System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
                    System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                }
            }
        }

        sc.close();
    }
}
