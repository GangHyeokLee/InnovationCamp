package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        List<Integer> results = new ArrayList<>();

        while (flag) {

            System.out.println("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            switch (input) {
                case "exit" -> flag = false;
                case "remove" -> results.removeFirst();
                case "inquiry" -> {
                    results.forEach(x -> System.out.print(x + " "));
                    System.out.println();
                }
                default -> {
                    int firstNum = Integer.parseInt(input);
                    System.out.println("두 번째 숫자를 입력하세요: ");
                    input = sc.nextLine();
                    int secondNum = Integer.parseInt(input);
                    System.out.println("사칙연산 기호를 입력하세요: ");
                    String operator = sc.nextLine();

                    int result = 0;
                    switch (operator) {
                        case "+":
                            result = firstNum + secondNum;
                            break;
                        case "-":
                            result = firstNum - secondNum;
                            break;
                        case "*":
                            result = firstNum * secondNum;
                            break;
                        case "/":
                            if (secondNum != 0) {
                                result = firstNum / secondNum;
                            } else {
                                System.out.println("나눗셈 연산에서 두 번째 정수에 0이 입력될 수 없습니다.");
                            }
                            break;
                        default:
                            System.out.println("입력 값이 올바르지 않습니다");
                            break;
                    }
                    System.out.println("결과: " + result);

                    results.add(result);
                    System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
                    System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
                    System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                }
            }
        }
    }

    static void add_result(int result, int[] results, int count) {
        if (count < 10) {
            results[count] = result;
            return;
        }

        for (int i = 1; i < 10; i++) {
            results[i - 1] = results[i];
        }
        results[9] = result;
    }
}
