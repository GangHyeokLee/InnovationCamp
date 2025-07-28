# SPRING ADVANCED

## Level 1
### Validation
```
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "비밀번호는 8자 이상이며, 숫자와 대문자를 포함해야 합니다."
    )
    private String newPassword;
```
newPassword에 Pattern 어노테이션 추가
---
Pattern에 맞지 않는 경우 client 측으로 Message 전달되지 않는 문제 있음


## Level 2
### N + 1 Problem
```
    @EntityGraph(attributePaths = {"user"})
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT t FROM Todo t WHERE t.id = :todoId")
```
EntityGraph를 사용하여 N + 1 문제 해결

## Level 3
### Test Code 1 - PassEncoderTest
```
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
```
passEncoder.matches() 메서드의 파라미터는 rawPassword와 encodedPassword 순서로 작성해야 함

### Test Code 2 - ManegerServiceTest
```
@Test
    public void manager_목록_조회_시_Todo가_없다면_IRE_에러를_던진다() {
        // given
        long todoId = 1L;
        given(todoRepository.findById(todoId)).willReturn(Optional.empty());

        // when & then
        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> managerService.getManagers(todoId));
        assertEquals("Todo not found", exception.getMessage());
    }
```
테스트 이름과 에러메시지를 수정하여 테스트 정상적으로 동작하게 함

### Test Code 3 - CommentServiceTest
```
        // when
        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            commentService.saveComment(authUser, todoId, request);
        });
```

CommentService 코드의
```
Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
        new InvalidRequestException("Todo not found"));
```
에 맞게 예외 타입을 수정함

### Test Code 4 - ManagerServiceTest
```
if (todo.getUser() == null || !ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())) {
            throw new InvalidRequestException("담당자를 등록하려고 하는 유저가 일정을 만든 유저가 유효하지 않습니다.");
        }
```
user가 null인경우를 체크하는 로직 추가