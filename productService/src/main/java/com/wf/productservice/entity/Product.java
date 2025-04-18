package com.wf.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Product entity class
// This class represents a product with various attributes.
@Entity
@Table(name = "product")
public class Product {
}