# ArithmeticCalculator 제네릭 변환 트러블슈팅

## 1. 산술 연산자와 제네릭 타입
- **문제:** Java의 제네릭 타입(T)에서는 `a + b`와 같은 산술 연산자를 직접 사용할 수 없음.
- **해결:** 타입별로 연산자 클래스를 분리(`AddOperatorInt`, `AddOperatorDouble` 등)하여 각각의 타입에 맞는 연산을 구현하고, App에서 최초 입력된 숫자의 타입을 탐지하여(`Integer.parseInt`, `Double.parseDouble` 등) 이에 따라 타입별 계산기(`ArithmeticCalculator<Integer>`, `ArithmeticCalculator<Double>`)가 동작하도록 구현함.

## 2. 값 비교 기능 추가
- **문제:** 제네릭 타입으로 인해 결과값 비교하는 것이 제한됨
- **해결:** ArithmeticCalculator 클래스에 제네릭 타입 입력받는 기능 제거. Integer와 Double 타입으로 입력이 제한되기에 double 타입으로 통합 후 최초 입력이 interger일 경우에만 integer로 출력. 굳이 불편하게 제네릭 유지할 이유가 없음.

---
# 피드백 수정
### Level 1
1. 빈 배열 확인 절차 추가
2. 사용 안 하는 메서드 제거 (add_result)
3. flag, success 변수 이름 변경 (isRunning, isSuccess)

### Level 2
1. 0 나눗셈, 유효하지 않은 연산자 사용 시 null 반환 및 상황에 맞는 메시지 출력

### Level 3
1. Compare 메서드 이름 변경 및 출력 모양 수정(줄바꿈 -> 공백)