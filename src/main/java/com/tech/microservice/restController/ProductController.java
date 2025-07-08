package com.tech.microservice.restController;

import com.tech.microservice.dto.ProductRequest;
import com.tech.microservice.dto.ProductResponse;
import com.tech.microservice.model.Product;
import com.tech.microservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        // Logic to retrieve a product by ID
        log.info("Retrieving product with ID: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        // Logic to retrieve all products
        log.info("Retrieving all products");
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        // Logic to update a product
        log.info("Updating product with ID: {} and request: {}", id, productRequest);
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        // Logic to delete a product
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
    }
}
