package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.ProductRequest;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseHandler.responseBuilder("product created", HttpStatus.CREATED,
                productService.saveProduct(productRequest));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody ProductRequest productRequest,
                                                @PathVariable Long productId){
        return ResponseHandler.responseBuilder("product updated", HttpStatus.OK,
                productService.updateProduct(productRequest,productId));
    }
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }
    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Object> getProductByCategory(@PathVariable Category category){
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }

}
