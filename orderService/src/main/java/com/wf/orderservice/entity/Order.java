package com.wf.orderservice.entity;

import com.netflix.spectator.api.Measurement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orders")
public class Order {

//    @Id
//    private String id;
//
//    @NotBlank(message = "Item name is required")
//    private String itemName;
//
//    @Min(value = 1, message = "Quantity must be at least 1")
//    private int quantity;
//
//    private double price;

    @Id
    private String id;
    private String userId;
    @NotBlank(message = "Item name is required")
    private String product;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    private double price;
    private String status;


}