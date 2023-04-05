package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryRequest;
import com.mokasoft.gestresto.dtos.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long categoryId);
    List<CategoryResponse> getCategories();
}
