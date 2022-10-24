package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.mappers.ProductMapper;
import com.mokasoft.gestresto.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        log.info("saving new Category");
        Category category = productMapper.fromCategoryDto(categoryDto);
        Category saveCategory = categoryRepository.save(category);
        return productMapper.fromCategory(saveCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().
                map(cat -> productMapper.fromCategory(cat)).collect(Collectors.toList());
        return categoryDtos;
    }
}
