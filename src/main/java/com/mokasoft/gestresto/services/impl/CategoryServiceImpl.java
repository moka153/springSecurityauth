package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.CategoryRequest;
import com.mokasoft.gestresto.dtos.CategoryResponse;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.CategoryMapper;
import com.mokasoft.gestresto.repositories.CategoryRepository;
import com.mokasoft.gestresto.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        try{
            Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
            return categoryMapper.categoryToCategoryResponse(categoryRepository.save(category));
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("category name already exists");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if(!categoryRepository.findById(categoryId).isPresent()){
            throw new NotFoundException("category not found");
        }
        try{
            categoryRepository.deleteById(categoryId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("category can't be deleted, category already used");
        }
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> categoryMapper.categoryToCategoryResponse(category))
                .collect(Collectors.toList());
        return categoryResponses;
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        if(!categoryRepository.findById(categoryId).isPresent()){
            throw new NotFoundException("category not found");
        }
        try{
            Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
            category.setCategoryId(categoryId);
            return categoryMapper.categoryToCategoryResponse(categoryRepository.save(category));
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("category name already exists");
        }

    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        if(!categoryRepository.findById(categoryId).isPresent()){
            throw new NotFoundException("category not found");
        }
        Category category = categoryRepository.findById(categoryId).get();
        return categoryMapper.categoryToCategoryResponse(category);
    }
}
