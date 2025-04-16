package com.wf.orderservice.controller;

import com.wf.orderservice.dto.OrderDTO;
import com.wf.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder( @RequestBody OrderDTO orderDTO){
        log.info("Creating order for userId : {}", orderDTO.getUserId());
        OrderDTO createOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id){
        log.info("Get order by id : {}", id);
        OrderDTO orderDTO = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Fetching all orders");
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO){
        log.info("Updating order by id : {}", id);
        OrderDTO updateOrder = orderService.updateOrder(id, orderDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        log.info("Deleting order with id: {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
    @GetMapping("/count-by-status")
    public ResponseEntity<?> getOrderCountGroupedByUser() {
        log.info("Fetching order count grouped by user");
        return ResponseEntity.ok(orderService.getOrderCountByStatus());
    }
}