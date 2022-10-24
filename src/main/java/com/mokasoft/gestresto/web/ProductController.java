package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.dtos.ProductDto;
import com.mokasoft.gestresto.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    //categories controller
    @GetMapping(path = "/products/categories")
    public List<CategoryDto> categories() {
        return productService.getAllCategories();
    }

    @PostMapping(path = "/products/categories")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return productService.saveCategory(categoryDto);
    }

    @PutMapping(path = "/products/categories/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setCategoryId(id);
        return productService.saveCategory(categoryDto);
    }


    @DeleteMapping("/products/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        productService.deleteCategory(id);
    }

    //products controller
    @GetMapping(path = "/products")
    public List<ProductDto> products() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/products")
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping(path = "/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        productDto.setProductId(id);
        return productService.saveProduct(productDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
