package com.wf.orderservice.service;

import com.wf.orderservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(String id);
    List<OrderDTO> getAllOrders();
    OrderDTO updateOrder(String id, OrderDTO orderDTO);
    Map<String,Long> getOrderCountByStatus();
    void deleteOrder(String id);
}