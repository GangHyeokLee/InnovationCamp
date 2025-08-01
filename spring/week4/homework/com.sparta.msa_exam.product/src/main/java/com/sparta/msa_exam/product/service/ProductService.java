package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.request.ProductSaveRequestDto;
import com.sparta.msa_exam.product.dto.response.ProductResponseDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream().map(ProductResponseDto::new).toList();
    }

    public ProductResponseDto saveProduct(ProductSaveRequestDto productSaveRequestDto) {
        Product product = new Product(productSaveRequestDto.getName(), productSaveRequestDto.getSupply_price());
        return new ProductResponseDto(productRepository.save(product));
    }
}
