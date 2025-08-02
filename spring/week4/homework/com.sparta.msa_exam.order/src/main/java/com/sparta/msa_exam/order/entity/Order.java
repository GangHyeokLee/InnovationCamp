package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1)
    private List<OrderedProduct> product_ids = new ArrayList<>();

    public void addOrderedProduct(Long product_id) {
        product_ids.add(new OrderedProduct(this, product_id));
    }
}
