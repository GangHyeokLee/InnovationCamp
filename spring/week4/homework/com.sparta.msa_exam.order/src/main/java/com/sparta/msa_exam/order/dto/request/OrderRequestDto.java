package com.sparta.msa_exam.order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {

    @NotNull
    @Size(min = 1, message = "상품 ID는 하나 이상 선택되어야 합니다.")
    private final List<Long> product_ids;

    public OrderRequestDto(List<Long> product_ids) {
        this.product_ids = product_ids;
    }
}
