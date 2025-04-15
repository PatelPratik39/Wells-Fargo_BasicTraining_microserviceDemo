package com.wf.orderservice.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private String product;
    private int quantity;
    private double price;
    private String status;
}