package com.sparta.msa_exam.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private Integer supply_price;
}
