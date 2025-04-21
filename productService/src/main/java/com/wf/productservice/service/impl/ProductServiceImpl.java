package com.wf.productservice.service.impl;

import com.wf.productservice.dto.ProductDTO;
import com.wf.productservice.entity.Product;
import com.wf.productservice.kafka.ProductKafkaProducer;
import com.wf.productservice.mapper.ProductMapper;
import com.wf.productservice.repository.ProductRepository;
import com.wf.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import com.wf.productservice.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductKafkaProducer productKafkaProducer;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.mapToEntity(productDTO));
        product.sendProductCreatedEvent(product);
        return productMapper.mapToDto(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
       Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
       return productMapper.mapToDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setAvailable(productDTO.isAvailable());

        Product updatedProduct = productRepository.save(product);
        productKafkaProducer.sendProductMessage(productMapper.mapToJson(updatedProduct));
        return productMapper.mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
        productRepository.sendProductDeletedEvent(product);
    }
}