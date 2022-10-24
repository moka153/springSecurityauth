package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

//categories controller
    @GetMapping(path = "/categories")
    public List<CategoryDto> categories(){
        return productService.getAllCategories();
    }

    @PostMapping(path = "/categories")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto){
        return productService.saveCategory(categoryDto);
    }

    @PutMapping(path = "/categories/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        categoryDto.setCategoryId(id);
        return productService.saveCategory(categoryDto);
    }


    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        productService.deleteCategory(id);
    }



}
