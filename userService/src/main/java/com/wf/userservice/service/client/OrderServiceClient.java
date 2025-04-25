package com.wf.userservice.service.client;

import com.wf.userservice.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {
    @PostMapping("/api/orders")
    OrderDTO createOrder(@RequestBody OrderDTO orderDTO);
}