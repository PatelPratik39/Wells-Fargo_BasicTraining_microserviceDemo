package com.wf.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private boolean available;
}