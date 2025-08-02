package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.request.OrderRequestDto;
import com.sparta.msa_exam.order.dto.response.OrderResponseDto;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.saveOrder(orderRequestDto);
    }
}
