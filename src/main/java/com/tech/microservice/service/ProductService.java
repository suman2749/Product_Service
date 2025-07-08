package com.tech.microservice.service;

import com.tech.microservice.dto.ProductRequest;
import com.tech.microservice.dto.ProductResponse;
import com.tech.microservice.model.Product;

import java.util.List;

public interface ProductService {
    // Define service methods for product operations here
    // For example:
     public List<ProductResponse> getAllProducts();
     public Product getProductById(String id);
     public ProductResponse createProduct(ProductRequest productRequest);
     public  Product updateProduct(String id, ProductRequest product);
     public void deleteProduct(String id);
}
