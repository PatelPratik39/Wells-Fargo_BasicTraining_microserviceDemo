package com.wf.orderservice.mapper;

import com.wf.orderservice.dto.OrderDTO;
import com.wf.orderservice.entity.Order;

public class OrderMapper {

    public static OrderDTO mapToOrder(Order order){
        if(order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setItemName(order.getItemName());
        dto.setQuantity(order.getQuantity());
        return dto;
    }
    public static Order mapToOrder(OrderDTO orderDTO){
        if(orderDTO == null) return null;

        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setItemName(orderDTO.getItemName());
        order.setQuantity(orderDTO.getQuantity());
        return order;
    }
}