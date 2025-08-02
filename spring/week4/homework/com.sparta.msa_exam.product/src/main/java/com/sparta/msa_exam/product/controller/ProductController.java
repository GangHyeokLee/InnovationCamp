package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.request.ProductSaveRequestDto;
import com.sparta.msa_exam.product.dto.response.ProductResponseDto;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto){
        return ResponseEntity.ok(productService.saveProduct(productSaveRequestDto));
    }
}
