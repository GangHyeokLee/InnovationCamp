# Bean Validation @Pattern을 사용한 Email 검증

## 과제 구현 내용

Bean Validation의 `@Pattern` 애너테이션으로 Email 형식(`계정@도메인.최상위도메인`) 검증을 구현했습니다.

### 1. Email 필드 추가
- **SignUpRequestDto**: `@NotBlank` + `@Pattern` 적용
- **TestDto**: `@NotBlank` + `@Pattern` 적용

```java
@Pattern(
    regexp = "^[^@.]+@[A-Za-z0-9]+\\.[A-Za-z]$",
    message = "이메일 형식이 올바르지 않습니다."
)
private String email;
```

### 2. 테스트 구현
`BeanValidationTest.java`에서 Email 제약조건 위반을 테스트:
- `@Pattern` 위반 (잘못된 이메일 형식)

### 결과
Email 제약조건 위반이 정상적으로 검증됨을 확인했습니다.
