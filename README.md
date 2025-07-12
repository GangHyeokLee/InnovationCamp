# ArithmeticCalculator 제네릭 변환 트러블슈팅

## 1. 산술 연산자와 제네릭 타입
- **문제:** Java의 제네릭 타입(T)에서는 `a + b`와 같은 산술 연산자를 직접 사용할 수 없음.
- **해결:** 타입별로 연산자 클래스를 분리(`AddOperatorInt`, `AddOperatorDouble` 등)하여 각각의 타입에 맞는 연산을 구현하고, App에서 최초 입력된 숫자의 타입을 탐지하여(`Integer.parseInt`, `Double.parseDouble` 등) 이에 따라 타입별 계산기(`ArithmeticCalculator<Integer>`, `ArithmeticCalculator<Double>`)가 동작하도록 구현함.
