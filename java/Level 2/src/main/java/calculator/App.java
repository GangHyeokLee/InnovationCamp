package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        List<Integer> results = new ArrayList<>();
        Calculator calculator = new Calculator();

        while (flag) {

            System.out.print("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            switch (input) {
                case "exit" -> flag = false;
                case "remove" -> results.remove(0);
                case "inquiry" -> {
                    results.forEach(x -> System.out.print(x + " "));
                    System.out.println();
                }
                default -> {
                    int firstNum = 0;
                    int secondNum = 0;
                    char operator;
                    try{
                        firstNum = Integer.parseInt(input);
                    }catch(NumberFormatException e){
                        System.out.println("숫자만 입력하세요.");
                        break;
                    }

                    try{
                        System.out.print("두 번째 숫자를 입력하세요: ");
                        input = sc.nextLine();
                        secondNum = Integer.parseInt(input);
                    }
                    catch (NumberFormatException e){
                        System.out.println("숫자만 입력하세요.");
                        break;
                    }

                    System.out.println("사칙연산 기호를 입력하세요: ");
                    operator = sc.nextLine().charAt(0);

                    int result = calculator.calculate(firstNum, secondNum, operator);
                    calculator.setResult(result);


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
