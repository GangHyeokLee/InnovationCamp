package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ordered_product")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    @NotNull
    private Long product_id;

    public OrderedProduct(Order order, Long productId) {
        this.order = order;
        this.product_id = productId;
    }
}
