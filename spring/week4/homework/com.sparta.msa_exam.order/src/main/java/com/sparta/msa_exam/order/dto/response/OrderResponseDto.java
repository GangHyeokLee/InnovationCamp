package com.sparta.msa_exam.order.dto.response;

import com.sparta.msa_exam.order.entity.OrderedProduct;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private final Long order_id;
    private final List<Long> product_ids;

    public OrderResponseDto(Long order_id, List<OrderedProduct> product_ids) {
        this.order_id = order_id;
        this.product_ids = product_ids.stream().map(OrderedProduct::getProduct_id).toList();
    }
}
