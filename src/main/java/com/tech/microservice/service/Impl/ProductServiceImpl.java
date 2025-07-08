package com.tech.microservice.service.Impl;

import com.tech.microservice.dto.ProductRequest;
import com.tech.microservice.dto.ProductResponse;
import com.tech.microservice.model.Product;
import com.tech.microservice.repository.ProductRepository;
import com.tech.microservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Retrieving all products");
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .toList();
        log.info("Total products retrieved: {}", response.size());
        return response;
    }
    @Override
    public Product getProductById(String id){
        log.info("Retrieving product with ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

    }
    @Override
    public ProductResponse createProduct(ProductRequest productrequest){
        log.info("Creating product with request: {}", productrequest);
      Product product1  =Product.builder()
                .id(productrequest.id())
                .name(productrequest.name())
                .description(productrequest.description())
                .price(productrequest.price())
                .build();
      productRepository.save(product1);
      log.info("Product created: {}", product1);
      return new ProductResponse(product1.getId(), product1.getName(), product1.getDescription(),product1.getPrice() );
    }
    @Override
    public Product updateProduct(String id, ProductRequest product) {
        log.info("Updating product with ID: {} and request: {}", id, product);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        existingProduct.setName(product.name());
        existingProduct.setDescription(product.description());
        existingProduct.setPrice(product.price());
        return productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(String id){
        log.info("Deleting product with ID: {}", id);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        productRepository.delete(existingProduct);
        log.info("Product with ID: {} deleted successfully", id);

    }
}
