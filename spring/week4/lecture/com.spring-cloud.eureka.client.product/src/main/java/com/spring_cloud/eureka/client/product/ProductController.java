package com.spring_cloud.eureka.client.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ProductController {

    @Value("${message}")
    private String message;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/product")
    public String getProduct() {
        return "Product info!!! From port : " + serverPort + " and message : " + message;
    }

}
