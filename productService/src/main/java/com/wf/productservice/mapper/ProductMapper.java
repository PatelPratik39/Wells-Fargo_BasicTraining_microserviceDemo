package com.wf.productservice.mapper;

import com.wf.productservice.dto.ProductDTO;
import com.wf.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // Convert entity to DTO
    public ProductDTO mapToDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId()); // include only if you need ID in response
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setAvailable(product.isAvailable());

        return productDTO;
    }

    // Convert DTO to entity
    public Product mapToEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setAvailable(productDTO.isAvailable());
        return product;
    }

    // Convert entity to JSON (for logging/debugging)
    public String mapToJson(Product product) {
        if (product == null) {
            return null;
        }

        return "{ \"id\": " + product.getId()
                + ", \"name\": \"" + product.getName()
                + "\", \"description\": \"" + product.getDescription()
                + "\", \"price\": " + product.getPrice()
                + ", \"quantity\": " + product.getQuantity()
                + ", \"available\": " + product.isAvailable()
                + " }";
    }
}