package com.sparta.msa_exam.order.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private final List<Long> product_ids;

    public OrderRequestDto(List<Long> product_ids) {
        this.product_ids = product_ids;
    }
}
