package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.entities.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public CategoryDto fromCategory(Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category,categoryDto);
        return categoryDto;
    }

    public Category fromCategoryDto(CategoryDto categoryDto){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        return category;
    }
}
