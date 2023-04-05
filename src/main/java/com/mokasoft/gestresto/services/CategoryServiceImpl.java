package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryRequest;
import com.mokasoft.gestresto.dtos.CategoryResponse;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.mappers.CategoryMapper;
import com.mokasoft.gestresto.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        return categoryMapper.categoryToCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        return null;
    }
}
