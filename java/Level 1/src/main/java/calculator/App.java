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
            boolean success = true;

            System.out.print("명령어를 입력하거나 첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            switch (input) {
                case "exit" -> flag = false;
                case "remove" -> {
                    if (!results.isEmpty()) {
                        results.remove(0);
                    } else {
                        System.out.println("삭제할 결과가 없습니다.");
                    }
                }
                case "inquiry" -> {
                    results.forEach(x -> System.out.print(x + " "));
                    System.out.println();
                }
                default -> {
                    int firstNum;
                    int secondNum;
                    char operator;
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
                    operator = sc.nextLine().charAt(0);

                    int result = 0;
                    switch (operator) {
                        case '+':
                            result = firstNum + secondNum;
                            break;
                        case '-':
                            result = firstNum - secondNum;
                            break;
                        case '*':
                            result = firstNum * secondNum;
                            break;
                        case '/':
                            if (secondNum != 0) {
                                result = firstNum / secondNum;
                            } else {
                                System.out.println("나눗셈 연산에서 두 번째 정수에 0이 입력될 수 없습니다.");
                                success = false;
                            }
                            break;
                        default:
                            System.out.println("입력 값이 올바르지 않습니다");
                            success = false;
                            break;
                    }
                    if (success) {
                        System.out.println("결과: " + result);
                        results.add(result);
                    }
                    System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
                    System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
                    System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                }
            }
        }
    }
}
