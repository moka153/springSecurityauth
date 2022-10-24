package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryDto;

import java.util.List;

public interface ProductService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    void deleteCategory(Long idCategory);
    List<CategoryDto> getAllCategories();
}
