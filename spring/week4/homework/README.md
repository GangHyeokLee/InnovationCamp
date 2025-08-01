# MSA 과제

## Issue 1 - 양방향 순환참조 문제 발생

### 문제 설명
Order와 OrdererdProduct 간의 양방향 순환참조로 인해 /orders POST API 호출 시 응답으로 엄청 길고 반복되는 JSON이 반환됨.

### 해결 방법
```
@Getter
public class OrderResponseDto {
    private final Long order_id;
    private final List<OrderedProduct> product_ids;

    public OrderResponseDto(Long order_id, List<OrderedProduct> product_ids) {
        this.order_id = order_id;
        this.product_ids = product_ids;
    }
}
```

OrderResponseDto에서 OrderedProduct자체를 반환하고 있었음
product_ids를 List<Long>으로 변경하여 순환참조 문제 해결

## Issue 2 - Circuit Breaker 적용 시 에러 처리

### 문제 설명
Circuit Breaker 적용 후, 주문 요청 시 상품이 존재하지 않는 경우에도 SERVICE_UNAVAILABLE 로 응답함.

### 해결 방법
```
public ResponseEntity<?> fallbackSaveOrder(OrderRequestDto orderRequestDto, Throwable t) {
        log.error("fallbackSaveOrder 실행됨 - 상품 API 호출 실패. 요청 상품 ID: {}. 예외: {}",
                orderRequestDto.getProduct_ids(), t.toString());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("잠시 후에 주문 추가를 요청 해주세요.");
    }
```
Throwable t의 instance를 확인하여 상품이 존재하지 않는 경우에는 기존 Error가 반환되도록 수정



# 과제 진행
## Load Balancing -> 실패
Load Balancing은 Spring boot [공식 문서](https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/loadbalancer.html)를 보고 진행하려고 했으나 도저히 안 돼서 강의 보고 진행하겠습니다.

----

# 질문
## 1. .env 파일 사용 유무
프론트엔드 개발할 때, key나 db 정보 등을 .env 파일에 넣어서 관리했는데, Spring Boot 개발할 때도 마찬가지인지 궁금합니다. 똑같이 .env 파일 사용하면 되는지 아니면 다른 방법이 있는지 궁금합니다.
