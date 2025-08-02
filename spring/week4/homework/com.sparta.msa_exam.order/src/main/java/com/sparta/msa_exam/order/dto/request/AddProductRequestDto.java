package com.sparta.msa_exam.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddProductRequestDto {

    @NotNull
    private final Long product_id;

    AddProductRequestDto(Long product_id) {
        this.product_id = product_id;
    }
}
