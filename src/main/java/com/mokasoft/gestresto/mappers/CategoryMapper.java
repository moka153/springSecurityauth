package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.CategoryRequest;
import com.mokasoft.gestresto.dtos.CategoryResponse;
import com.mokasoft.gestresto.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category categoryRequestToCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .picture(categoryRequest.getPicture())
                .build();
        return category;
    }

    public CategoryResponse categoryToCategoryResponse(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .picture(category.getPicture())
                .build();
        return categoryResponse;
    }
}
