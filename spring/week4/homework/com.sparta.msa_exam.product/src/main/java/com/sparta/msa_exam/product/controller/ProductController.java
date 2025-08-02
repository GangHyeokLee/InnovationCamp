package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.request.ProductSaveRequestDto;
import com.sparta.msa_exam.product.dto.response.ProductResponseDto;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto){
        return ResponseEntity.ok(productService.saveProduct(productSaveRequestDto));
    }

    // 로드밸런싱 테스트용
    @GetMapping("/port")
    public ResponseEntity<String> getPort(){
        return ResponseEntity.ok().body(serverPort);
    }
}
