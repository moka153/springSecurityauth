package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.ProductRequest;
import com.mokasoft.gestresto.dtos.ProductResponse;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Product;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.ProductMapper;
import com.mokasoft.gestresto.repositories.CategoryRepository;
import com.mokasoft.gestresto.repositories.ProductRepository;
import com.mokasoft.gestresto.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        if(!categoryRepository.findById(productRequest.getCategoryId()).isPresent()){
            throw new NotFoundException("category not found");
        }
        try {
            Product product = productMapper.productRequestToProduct(productRequest);
            Product savedProduct = productRepository.save(product);
            return productMapper.productToProductResponse(savedProduct);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Product already exists");
        }
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        if(!categoryRepository.findById(productRequest.getCategoryId()).isPresent()){
            throw new NotFoundException("category not found");
        }
        if(!productRepository.findById(productId).isPresent()){
            throw new NotFoundException("Product not found");
        }
        try {
            Product product = productMapper.productRequestToProduct(productRequest);
            product.setProductId(productId);
            Product savedProduct = productRepository.save(product);
            return productMapper.productToProductResponse(savedProduct);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Product already exists");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        if(!productRepository.findById(productId).isPresent()){
            throw new NotFoundException("Product not found");
        }
        try{
            productRepository.deleteById(productId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Product already used");
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());
        return productResponses;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            return productMapper.productToProductResponse(product);
        }catch (RuntimeException ex){
            throw new NotFoundException("Product not found");
        }
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Category category) {
        //TODO revoir l'exception l'orsque l'id categorie n'existe pas
        if(!categoryRepository.findById(category.getCategoryId()).isPresent()){
            throw new NotFoundException("category not found");
        }
        List<Product> products = productRepository.findProductByCategory(category);
        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());
        return productResponses;
    }
}
