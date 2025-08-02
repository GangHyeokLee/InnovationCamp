package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.request.OrderRequestDto;
import com.sparta.msa_exam.order.dto.response.OrderResponseDto;
import com.sparta.msa_exam.order.dto.response.ProductResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    // 실제로 있는 상품인지 확인 후 DB에 저장하기
    @Transactional
    @CircuitBreaker(
            name = "orderService",
            fallbackMethod = "fallbackSaveOrder"
    )
    public ResponseEntity<OrderResponseDto> saveOrder(OrderRequestDto orderRequestDto) {
        log.info("주문 요청 수신: {}", orderRequestDto.getProduct_ids());

        // 기존 상품 목록의 id 불러오기, Set -> O(1)의 탐색 시간
        Set<Long> availableProducts = productClient.getProducts()
                .stream()
                .map(ProductResponseDto::getId)
                .collect(Collectors.toSet());

        // Order 엔티티 생성 및 저장
        Order order = new Order();

        // 존재하지 않는 상품 ID가 있는지 확인
        for (Long productId : orderRequestDto.getProduct_ids()) {
            if (!availableProducts.contains(productId)) {
                log.warn("존재하지 않는 상품 ID 요청: {}", productId);
                throw new IllegalArgumentException("존재하지 않는 상품 ID: " + productId);
            }
            order.addOrderedProduct(productId);
        }

        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(new OrderResponseDto(
                savedOrder.getOrder_id(),
                savedOrder.getProduct_ids()
        ));
    }

    public ResponseEntity<?> fallbackSaveOrder(OrderRequestDto orderRequestDto, Throwable t) {
        log.error("fallbackSaveOrder 실행됨 - 상품 API 호출 실패. 요청 상품 ID: {}. 예외: {}",
                orderRequestDto.getProduct_ids(), t.toString());
        
        // 비즈니스 로직 예외들은 fallback이 아닌 정상적인 예외로 처리
        if (t instanceof IllegalArgumentException) {
            throw (RuntimeException) t;
        }
        
        // 실제 서비스 장애 시에만 fallback 메시지 반환
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("잠시 후에 주문 추가를 요청 해주세요.");
    }
}
