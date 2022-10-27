package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.dtos.OptionDto;
import com.mokasoft.gestresto.dtos.ProductDto;
import com.mokasoft.gestresto.entities.Category;
import com.mokasoft.gestresto.entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    CategoryDto saveCategory(CategoryDto categoryDto) ;
    void deleteCategory(Long idCategory);
    List<CategoryDto> getAllCategories();
    ProductDto saveProduct(ProductDto productDto);
    void deleteProduct(Long idProduct);
    List<ProductDto> getAllProducts();

    OptionDto saveOption(OptionDto optionDto);
    void deleteOption(Long idOption);
    List<OptionDto> options();

    List<ProductDto> getProductByCategory(Category category);

    List<OptionDto> getProductOptions(ProductDto productDto);
}
