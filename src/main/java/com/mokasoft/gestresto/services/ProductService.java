package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.ProductRequest;
import com.mokasoft.gestresto.dtos.ProductResponse;
import com.mokasoft.gestresto.entities.Category;

import java.util.List;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest,Long productId);
    void deleteProduct(Long productId);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long productId);
    List<ProductResponse> getProductsByCategory(Category category);
}
