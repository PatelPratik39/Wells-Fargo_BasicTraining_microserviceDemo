package com.wf.productservice.service;

import com.wf.productservice.dto.ProductDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}