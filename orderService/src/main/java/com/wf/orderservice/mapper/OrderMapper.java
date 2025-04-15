package com.wf.orderservice.mapper;

import com.wf.orderservice.dto.OrderDTO;
import com.wf.orderservice.entity.Order;

public class OrderMapper {
    public static Order mapToOrder(OrderDTO dto) {
        return Order.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .product(dto.getProduct())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .build();
    }

    public static OrderDTO mapToOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setProduct(order.getProduct());
        dto.setQuantity(order.getQuantity());
        dto.setPrice(order.getPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }
}