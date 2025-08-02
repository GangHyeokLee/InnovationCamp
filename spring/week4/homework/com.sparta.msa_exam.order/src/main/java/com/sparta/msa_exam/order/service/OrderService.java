package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.request.AddProductRequestDto;
import com.sparta.msa_exam.order.dto.request.OrderRequestDto;
import com.sparta.msa_exam.order.dto.response.OrderResponseDto;
import com.sparta.msa_exam.order.dto.response.ProductResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
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

        return new OrderResponseDto(
                savedOrder.getOrder_id(),
                savedOrder.getProduct_ids()
        );
    }



    @Transactional
    @CircuitBreaker(
            name = "orderService",
            fallbackMethod = "fallbackAddProduct"
    )
    public OrderResponseDto addProduct(AddProductRequestDto addProductRequestDto, Integer orderId) {
        Long product_id = addProductRequestDto.getProduct_id();
        log.info("주문 ID: {}에 상품 ID: {} 추가 요청", orderId, product_id);

        // 주문 존재 여부 확인
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new IllegalArgumentException("존재하지 않는 주문 ID: " + orderId)
        );

        // 기존 상품 목록의 id 불러오기, Set -> O(1)의 탐색 시간
        Set<Long> availableProducts = productClient.getProducts()
                .stream()
                .map(ProductResponseDto::getId)
                .collect(Collectors.toSet());

        // 존재하지 않는 상품 ID인지 확인
        if (!availableProducts.contains(product_id)) {
            log.warn("존재하지 않는 상품 ID 요청: {}", product_id);
            throw new IllegalArgumentException("존재하지 않는 상품 ID: " + product_id);
        }

        // 이미 주문에 포함된 상품인지 확인
        boolean isAlreadyOrdered = order.getProduct_ids().stream()
                .anyMatch(orderedProduct -> orderedProduct.getProduct_id().equals(product_id));
        
        if (isAlreadyOrdered) {
            log.warn("이미 주문에 포함된 상품 ID: {}", product_id);
            throw new IllegalArgumentException("이미 주문에 포함된 상품 ID: " + product_id);
        }

        // 상품을 주문에 추가
        order.addOrderedProduct(product_id);
        Order savedOrder = orderRepository.save(order);

        log.info("주문 ID: {}에 상품 ID: {} 추가 완료", orderId, product_id);

        return new OrderResponseDto(
                savedOrder.getOrder_id(),
                savedOrder.getProduct_ids()
        );
    }

    public OrderResponseDto findOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 주문 ID: " + orderId)
        );
        return new OrderResponseDto(order.getOrder_id(), order.getProduct_ids());
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

    public ResponseEntity<?> fallbackAddProduct(AddProductRequestDto addProductRequestDto, Integer orderId, Throwable t) {
        log.error("fallbackAddProduct 실행됨 - 상품 API 호출 실패. 주문 ID: {}, 상품 ID: {}. 예외: {}",
                orderId, addProductRequestDto.getProduct_id(), t.toString());

        // 비즈니스 로직 예외들은 fallback이 아닌 정상적인 예외로 처리
        if (t instanceof IllegalArgumentException) {
            throw (RuntimeException) t;
        }

        // 실제 서비스 장애 시에만 fallback 메시지 반환
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("잠시 후에 상품 추가를 요청 해주세요.");
    }


}
