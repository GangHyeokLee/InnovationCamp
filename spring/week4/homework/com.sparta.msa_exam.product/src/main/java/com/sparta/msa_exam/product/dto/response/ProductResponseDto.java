package com.sparta.msa_exam.product.dto.response;

import com.sparta.msa_exam.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private final Long id;
    private final String name;
    private final Integer supply_price;

    public ProductResponseDto(Product product) {
        this.id = product.getProduct_id();
        this.name = product.getName();
        this.supply_price = product.getSupply_price();
    }

}
