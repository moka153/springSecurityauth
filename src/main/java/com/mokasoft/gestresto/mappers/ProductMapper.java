package com.mokasoft.gestresto.mappers;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.dtos.ProductDto;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Product;
import com.mokasoft.gestresto.enums.ProductType;
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

    public ProductDto fromProduct(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        productDto.setCategoryDto(fromCategory(product.getCategory()));
        return productDto;
    }
    public Product fromProductDto(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        product.setCategory(fromCategoryDto(productDto.getCategoryDto()));
        return product;
    }
}
