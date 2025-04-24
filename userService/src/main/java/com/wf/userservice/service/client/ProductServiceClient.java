package com.wf.userservice.service.client;

import com.wf.userservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceClient {
    @PostMapping("/api/products")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO);
}