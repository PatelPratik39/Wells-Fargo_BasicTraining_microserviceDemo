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
    private final ProductMapper productMapper;
    private final ProductKafkaProducer kafkaProducer;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = productMapper.mapToEntity(dto);
        Product saved = productRepository.save(product);
        kafkaProducer.sendProductMessage(productMapper.mapToJson(saved));
        return productMapper.mapToDto(saved);
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
        kafkaProducer.sendProductMessage(productMapper.mapToJson(updatedProduct));
        return productMapper.mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
        kafkaProducer.sendProductMessage("Product deleted with ID: " + id);

    }
}